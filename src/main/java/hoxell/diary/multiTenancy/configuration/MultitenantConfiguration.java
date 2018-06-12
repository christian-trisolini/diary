package hoxell.diary.multiTenancy.configuration;

import hoxell.diary.database.repositories.TenantRepository;
import hoxell.diary.model.Tenant;
import hoxell.diary.multiTenancy.MultitenantDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MultitenantConfiguration {

    @Autowired
    private DataSourceProperties properties;

    private static Map<Object,Object> resolvedDataSources = new HashMap<>();
    private static MultitenantDataSource dataSource = new MultitenantDataSource();
    @Autowired
    private TenantRepository tenantRepository;

    public static Map<Object, Object> getResolvedDataSources() {
        return new HashMap<>(resolvedDataSources);
    }


    /**
     * Creates the default data source for the application
     * @return
     */
    private DataSource defaultDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader())
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword());

        if(properties.getType() != null) {
            dataSourceBuilder.type(properties.getType());
        }

        return dataSourceBuilder.build();
    }

    public static void setResolvedDataSources(Map<Object, Object> resolvedDataSources) {
        MultitenantConfiguration.resolvedDataSources = new HashMap<>(resolvedDataSources);
        dataSource.setTargetDataSources(resolvedDataSources);
        dataSource.afterPropertiesSet();
    }

    /**
     * Defines the data source for the application
     * @return
     */
    @Bean(name = "dataSources" )
    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    public DataSource dataSource() {

        List<Tenant> tenants = tenantRepository.findAll();

        tenants.forEach(t -> {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader());
            dataSourceBuilder.driverClassName(t.getDriverClassName())
                    .url(t.getUrl())
                    .username(t.getUsername())
                    .password(t.getPassword());
            resolvedDataSources.put(t.getName(), dataSourceBuilder.build());
        });

        dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(resolvedDataSources);
        dataSource.afterPropertiesSet();

        return dataSource;

/*        File[] files = Paths.get("tenants").toFile().listFiles();


        for(File propertyFile : files) {
            Properties tenantProperties = new Properties();
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(this.getClass().getClassLoader());

            try {
                tenantProperties.load(new FileInputStream(propertyFile));

                String tenantId = tenantProperties.getProperty("name");

                dataSourceBuilder.driverClassName(com.mysql.jdbc.Driver.class.getName())
                        .url(tenantProperties.getProperty("datasource.url"))
                        .username(tenantProperties.getProperty("datasource.username"))
                        .password(tenantProperties.getProperty("datasource.password"));

                if(properties.getType() != null) {
                    dataSourceBuilder.type(properties.getType());
                }

                resolvedDataSources.put(tenantId, dataSourceBuilder.build());
            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }

        }

        // Create the final multi-tenant source.
        // It needs a default database to connect to.
        // Make sure that the default database is actually an empty tenant database.
        // Don't use that for a regular tenant if you want things to be safe!
        dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(resolvedDataSources);

        // Call this to finalize the initialization of the data source.
        dataSource.afterPropertiesSet();

        return dataSource;*/
    }
}
