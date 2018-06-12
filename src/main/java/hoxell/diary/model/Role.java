package hoxell.diary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToMany(mappedBy = "role")
    private List<HoxUser> users;

    public String getAuthority() {
        return name();
    }

    public List<HoxUser> getUsers() {
        return users;
    }

    public void setUsers(List<HoxUser> users) {
        this.users = users;
    }
}
