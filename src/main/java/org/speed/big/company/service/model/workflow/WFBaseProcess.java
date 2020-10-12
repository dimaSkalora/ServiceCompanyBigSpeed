package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NamedQueries({
        @NamedQuery(name = WFBaseProcess.DELETE, query = "delete from WFBaseProcess wfbp where wfbp.id=:id"),
        @NamedQuery(name = WFBaseProcess.GET, query = "select wfbp from WFBaseProcess wfbp where wfbp.id=:id"),
        @NamedQuery(name = WFBaseProcess.ALL_SORTED, query = "select wfbp from WFBaseProcess wfbp " +
                " order by wfbp.name")
})
@Entity
@Table(name = "wf_base_process")
public class WFBaseProcess extends AbstractBaseEntity {

    public final static String DELETE = "WFBaseProcess.delete";
    public final static String GET = "WFBaseProcess.get";
    public final static String ALL_SORTED = "WFBaseProcess.allSorted";

    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_service_id")
    private WFService wfServiceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_base_process_type_id")
    private WFBaseProcessType wfBaseProcessTypeId;

    public WFBaseProcess() {
    }

    public WFBaseProcess(Integer id, @NotBlank String name, @NotBlank String description, WFService wfServiceId, WFBaseProcessType wfBaseProcessTypeId) {
        super(id);
        this.name = name;
        this.description = description;
        this.wfServiceId = wfServiceId;
        this.wfBaseProcessTypeId = wfBaseProcessTypeId;
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

    public WFService getWfServiceId() {
        return wfServiceId;
    }

    public void setWfServiceId(WFService wfServiceId) {
        this.wfServiceId = wfServiceId;
    }

    public WFBaseProcessType getWfBaseProcessTypeId() {
        return wfBaseProcessTypeId;
    }

    public void setWfBaseProcessTypeId(WFBaseProcessType wfBaseProcessTypeId) {
        this.wfBaseProcessTypeId = wfBaseProcessTypeId;
    }

    @Override
    public String toString() {
        return "WFBaseProcess{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", wfServiceId=" + wfServiceId +
                ", wfBaseProcessTypeId=" + wfBaseProcessTypeId +
                ", id=" + id +
                '}';
    }
}
