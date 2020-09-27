package org.speed.big.company.service.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role extends AbstractBaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
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
