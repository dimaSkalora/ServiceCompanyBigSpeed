package org.speed.big.company.service.model.workflow;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = WFProcessStatus.DELETE, query = "delete from WFProcessStatus where id=:id"),
        @NamedQuery(name = WFProcessStatus.GET, query = "select wfps from WFProcessStatus wfps where wfps.id=:id"),
        @NamedQuery(name = WFProcessStatus.ALL_SORTED, query = "select wfps from WFProcessStatus  wfps " +
                "order by wfps.name")
})
@Entity
@Table(name = "wf_process_status")
public class WFProcessStatus extends WFAbstractBaseEntity {

    public static final String DELETE = "WFProcessStatus.delete";
    public static final String GET = "WFProcessStatus.get";
    public static final String ALL_SORTED = "WFProcessStatus.allSorted";

    public static final int IN_WORK = 1;
    public static final int COMPLETED = 2;
    public static final int WAITING = 3;
    public static final int ARCHIVE = 4;

    @NotBlank
    @Size(min = 5, max = 50)
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
