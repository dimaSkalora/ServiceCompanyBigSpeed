package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NamedQueries({
        @NamedQuery(name = WFBaseProcessType.DELETE,query = "delete from WFBaseProcessType wfbpt where wfbpt.id=:id"),
        @NamedQuery(name = WFBaseProcessType.GET,query = "select wfbpt from WFBaseProcessType wfbpt where wfbpt.id=:id"),
        @NamedQuery(name = WFBaseProcessType.ALL_SORTED,query = "select wfbpt from WFBaseProcessType wfbpt " +
                " where wfbpt.id=:id order by wfbpt.name")
})
@Entity
@Table(name = "wf_base_process_type")
public class WFBaseProcessType extends AbstractBaseEntity {
    public static final String DELETE = "WFBaseProcessType.delete";
    public static final String GET = "WFBaseProcessType.get";
    public static final String ALL_SORTED = "WFBaseProcessType.allSorted";

    @NotBlank
    @Column(nullable = false)
    public String name;

    public WFBaseProcessType() {
    }

    public WFBaseProcessType(Integer id, String name) {
        super(id);
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
        return "WFBaseProcessType{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
