package org.speed.big.company.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Role.DELETE, query = "DELETE from Role r where r.id=:id"),
        //@NamedQuery(name = Role.GET, query = "select r from Role r where r.id=:id"),
        @NamedQuery(name = Role.GET, query = "select r from Role r join fetch r.roleTypeId where r.id=:id"),
        @NamedQuery(name = Role.GET_FROM_ALL_USERS, query = "select DISTINCT r from Role r join fetch r.roleTypeId " +
                " join fetch r.userList where r.id=:id"),
        //@NamedQuery(name = Role.ALL_SORTED, query = "select r from Role r order by r.name")
        @NamedQuery(name = Role.ALL_SORTED, query = "select r from Role r join fetch r.roleTypeId order by r.name")
})
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "role_type_id"},
                        name = "roles_unique_name_role_type_idx"))
//@JsonIgnoreProperties(ignoreUnknown = false)
//@Proxy(lazy = false)
public class Role extends AbstractBaseEntity{

    public static final String DELETE = "Role.delete";
    public static final String GET = "Role.get";
    public static final String GET_FROM_ALL_USERS = "Role.getFromAllUsers";
    public static final String ALL_SORTED = "Role.allSorted";

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "name", nullable = false)
    private String name;
    @NotBlank
    @Size(min = 5, max = 250)
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_type_id", nullable = false)
    private RoleType roleTypeId;

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (name="user_roles",
            joinColumns=@JoinColumn (name="role_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> userList;

    public Role() {
    }

    public Role(Integer id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public Role(Integer id, String name, String description, RoleType roleTypeId) {
        super(id);
        this.name = name;
        this.description = description;
        this.roleTypeId = roleTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleType getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(RoleType roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", roleTypeId=" + roleTypeId +
                ", id=" + id +
                '}';
    }
}
