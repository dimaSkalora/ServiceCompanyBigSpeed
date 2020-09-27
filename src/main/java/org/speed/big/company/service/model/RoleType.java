package org.speed.big.company.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = RoleType.DELETE, query = "DELETE FROM RoleType rt WHERE rt.id=:id"),
        @NamedQuery(name = RoleType.ALL_SORTED, query = "SELECT rt FROM RoleType rt order by rt.name")
        }
)
@Entity
@Table(name = "role_type")
public class RoleType extends AbstractBaseEntity{

    public static final String DELETE = "RoleType.delete";
    public static final String ALL_SORTED = "RoleType.allSorted";

    @NotBlank
    @Size(min = 4)
    private String name;
    @OneToMany(mappedBy = "roleTypeId")
    private List<Role> roles;

    public RoleType() {
    }

    public RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleType{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
