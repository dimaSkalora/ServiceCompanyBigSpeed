package org.speed.big.company.service.model.workflow;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = WFBaseProcess.DELETE, query = "delete from WFBaseProcess wfbp where wfbp.id=:id"),
        @NamedQuery(name = WFBaseProcess.GET, query = "select wfbp from WFBaseProcess wfbp " +
                " join fetch wfbp.wfServiceId join fetch wfbp.wfBaseProcessTypeId " +
                " where wfbp.id=:id"),
        @NamedQuery(name = WFBaseProcess.ALL_SORTED, query = "select wfbp from WFBaseProcess wfbp " +
                " join fetch wfbp.wfServiceId join fetch wfbp.wfBaseProcessTypeId " +
                " order by wfbp.name")
})
@Entity
@Table(name = "wf_base_process", indexes = {
        @Index(name = "wfbpro_idx_wfsserid", columnList = "wf_service_id"),
        @Index(name = "wfbpro_idx_wfbptypeid", columnList = "wf_base_process_type_id")
})
public class WFBaseProcess extends WFAbstractBaseEntity {

    public final static String DELETE = "WFBaseProcess.delete";
    public final static String GET = "WFBaseProcess.get";
    public final static String ALL_SORTED = "WFBaseProcess.allSorted";

    @NotBlank
    @Size(min = 5, max = 150)
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_service_id", nullable = false)
    private WFService wfServiceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_base_process_type_id", nullable = false)
    private WFBaseProcessType wfBaseProcessTypeId;

    public WFBaseProcess() {
    }

    public WFBaseProcess(Integer id, @NotBlank String name, String description, WFService wfServiceId, WFBaseProcessType wfBaseProcessTypeId) {
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
