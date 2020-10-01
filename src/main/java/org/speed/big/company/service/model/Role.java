package org.speed.big.company.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@NamedQueries({
        @NamedQuery(name = Role.DELETE, query = "DELETE from Role r where r.id=:id"),
        @NamedQuery(name = Role.GET, query = "select r from Role r where r.id=:id"),
        @NamedQuery(name = Role.ALL_SORTED, query = "select r from Role r order by r.name")
})
@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "role_type_id"},
                        name = "roles_unique_name_role_type_idx"))
public class Role extends AbstractBaseEntity{

    public static final String DELETE = "Role.delete";
    public static final String GET = "Role.get";
    public static final String ALL_SORTED = "Role.allSorted";

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_type_id")
    private RoleType roleTypeId;

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
