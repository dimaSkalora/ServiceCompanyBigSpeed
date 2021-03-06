package org.speed.big.company.service.model.workflow;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = WFBaseProcessType.DELETE,query = "delete from WFBaseProcessType wfbpt where wfbpt.id=:id"),
        @NamedQuery(name = WFBaseProcessType.GET,query = "select wfbpt from WFBaseProcessType wfbpt where wfbpt.id=:id"),
        @NamedQuery(name = WFBaseProcessType.ALL_SORTED,query = "select wfbpt from WFBaseProcessType wfbpt " +
                " order by wfbpt.name")
})
@Entity
@Table(name = "wf_base_process_type")
public class WFBaseProcessType extends WFAbstractBaseEntity {
    public static final String DELETE = "WFBaseProcessType.delete";
    public static final String GET = "WFBaseProcessType.get";
    public static final String ALL_SORTED = "WFBaseProcessType.allSorted";

    @NotBlank
    @Size(min = 4, max = 1150)
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
