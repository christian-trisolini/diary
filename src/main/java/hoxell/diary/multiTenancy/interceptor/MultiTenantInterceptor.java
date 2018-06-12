package hoxell.diary.multiTenancy.interceptor;

import hoxell.diary.database.repositories.TenantRepository;
import hoxell.diary.model.Tenant;
import hoxell.diary.multiTenancy.TenantContext;
import hoxell.diary.multiTenancy.configuration.MultitenantConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;


public class MultiTenantInterceptor extends HandlerInterceptorAdapter {

    private static final String TENANT_HEADER_NAME = "X-TenantID";

    @Autowired
    TenantRepository tenantRepository;

    private Map<Object,Object> dataSources;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenant = request.getHeader(TENANT_HEADER_NAME);
        if(tenant == null) return true;
        dataSources = MultitenantConfiguration.getResolvedDataSources();
        if (!dataSources.containsKey(tenant)) {
            addTenant(tenant);
            MultitenantConfiguration.setResolvedDataSources(dataSources);
        }
        TenantContext.setCurrentTenant(tenant);
        return true;
    }

    private void addTenant(String tenant) {

        Tenant t = new Tenant();
        t.setName(tenant);
        t.setPassword("");
        t.setUsername("root");
        t.setDriverClassName("com.mysql.jdbc.Driver");
        t.setUrl("jdbc:mysql://localhost:3306/" + tenant);

        tenantRepository.save(t);

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(MultitenantConfiguration.class.getClassLoader())
                .driverClassName("com.mysql.jdbc.Driver")
                .username("root")
                .password("")
                .url("jdbc:mysql://localhost:3306/" + tenant);
        dataSources.put(tenant, dataSourceBuilder.build());

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {
        TenantContext.clear();
    }

}
