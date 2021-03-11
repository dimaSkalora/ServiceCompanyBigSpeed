package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = WFProcess.DELETE, query = "delete from WFProcess where id=:id"),
        @NamedQuery(name = WFProcess.GET, query = "select wfp from WFProcess wfp where wfp.id=:id"),
        @NamedQuery(name = WFProcess.ALL_SORTED, query = "select wfp from WFProcess wfp " +
                " order by wfp.startDate DESC") // DESC - по убыванию.
})
@Entity
@Table(name = "wf_process", indexes = {
        @Index(name = "wfpro_idx_wfpackid", columnList = "wf_package_id"),
        @Index(name = "wfpro_idx_wfbprocessid", columnList = "wf_base_process_id"),
        @Index(name = "wfpro_idx_wfpstatusid", columnList = "wf_process_status_id")
})
public class WFProcess extends WFAbstractBaseEntity {
    public static final String DELETE = "WFProcess.delete";
    public static final String GET = "WFProcess.get";
    public static final String ALL_SORTED = "WFProcess.allSorted";

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "final_date")
    private LocalDateTime finalDate;
    private String description;
    @NotNull
    @Column(name = "date_edit", nullable = false)
    private LocalDateTime dateEdit;
    @NotNull
    @Column(name = "user_edit", nullable = false)
    private String userEdit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_package_id", nullable = false)
    private WFPackage wfPackageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_base_process_id", nullable = false)
    private WFBaseProcess wfBaseProcessId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_process_status_id", nullable = false)
    private WFProcessStatus wfProcessStatusId;

    public WFProcess() {
    }

    public WFProcess(Integer id, LocalDateTime startDate, LocalDateTime finalDate, String description, LocalDateTime dateEdit, String userEdit, WFPackage wfPackageId, WFBaseProcess wfBaseProcessId, WFProcessStatus wfProcessStatusId) {
        super(id);
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.description = description;
        this.dateEdit = dateEdit;
        this.userEdit = userEdit;
        this.wfPackageId = wfPackageId;
        this.wfBaseProcessId = wfBaseProcessId;
        this.wfProcessStatusId = wfProcessStatusId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getUserEdit() {
        return userEdit;
    }

    public void setUserEdit(String userEdit) {
        this.userEdit = userEdit;
    }

    public WFPackage getWfPackageId() {
        return wfPackageId;
    }

    public void setWfPackageId(WFPackage wfPackageId) {
        this.wfPackageId = wfPackageId;
    }

    public WFBaseProcess getWfBaseProcessId() {
        return wfBaseProcessId;
    }

    public void setWfBaseProcessId(WFBaseProcess wfBaseProcessId) {
        this.wfBaseProcessId = wfBaseProcessId;
    }

    public WFProcessStatus getWfProcessStatusId() {
        return wfProcessStatusId;
    }

    public void setWfProcessStatusId(WFProcessStatus wfProcessStatusId) {
        this.wfProcessStatusId = wfProcessStatusId;
    }

    @Override
    public String toString() {
        return "WFProcess{" +
                "startDate=" + startDate +
                ", finalDate=" + finalDate +
                ", description='" + description + '\'' +
                ", dateEdit=" + dateEdit +
                ", userEdit='" + userEdit + '\'' +
                ", wfPackageId=" + wfPackageId +
                ", wfBaseProcessId=" + wfBaseProcessId +
                ", wfProcessStatusId=" + wfProcessStatusId +
                ", id=" + id +
                '}';
    }
}
