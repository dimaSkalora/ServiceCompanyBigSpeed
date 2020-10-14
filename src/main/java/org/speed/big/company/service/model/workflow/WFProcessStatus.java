package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NamedQueries({
        @NamedQuery(name = WFProcessStatus.DELETE, query = "delete from WFBaseProcess where id=:id"),
        @NamedQuery(name = WFBaseProcess.GET, query = "select wfps from WFBaseProcess wfps where wfps.id=:id"),
        @NamedQuery(name = WFBaseProcess.ALL_SORTED, query = "select wfps from WFBaseProcess  wfps " +
                "order by wfps.name")
})
@Entity
@Table(name = "wf_process_status")
public class WFProcessStatus extends AbstractBaseEntity {

    public static final String DELETE = "WFProcessStatus.delete";
    public static final String GET = "WFProcessStatus.get";
    public static final String ALL_SORTED = "WFProcessStatus.allSorted";

    @NotBlank
    @Column(nullable = false)
    private String name;

    public WFProcessStatus() {
    }

    public WFProcessStatus(Integer id, String name) {
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
        return "WFProcessStatus{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
