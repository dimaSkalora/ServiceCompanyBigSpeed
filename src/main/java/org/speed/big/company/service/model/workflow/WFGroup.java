package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NamedQueries({
        @NamedQuery(name = WFGroup.DELETE, query = "delete from WFGroup wfg where wfg.id=:id"),
        @NamedQuery(name = WFGroup.GET, query = "select wfg from WFGroup wfg where wfg.id=:id"),
        @NamedQuery(name = WFGroup.ALL_SORTED, query = "select wfg from WFGroup wfg " +
                "order by wfg.name")
})
@Entity
@Table(name = "wf_group")
public class WFGroup extends AbstractBaseEntity {
    public static final String DELETE = "WFGroup.delete";
    public static final String GET = "WFGroup.get";
    public static final String ALL_SORTED = "WFGroup.allSorted";

    @NotBlank
    @Column(nullable = false)
    private String name;
    private String description;

    public WFGroup() {
    }

    public WFGroup(Integer id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "WFGroup{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
