package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;
import org.speed.big.company.service.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = WFProcessMovement.DELETE, query = "delete from WFProcessMovement where id=:id"),
        @NamedQuery(name = WFProcessMovement.GET, query = "select wfpm from WFProcessMovement wfpm " +
                " join fetch wfpm.userId join fetch wfpm.wfPackageId " +
                " join fetch wfpm.wfStateId join fetch wfpm.wfProcessId " +
                " join fetch wfpm.wfBaseProcessId " +
                " where wfpm.id=:id"),
        @NamedQuery(name = WFProcessMovement.ALL_SORTED, query = "select wfpm from WFProcessMovement wfpm " +
                " join fetch wfpm.userId join fetch wfpm.wfPackageId " +
                " join fetch wfpm.wfStateId join fetch wfpm.wfProcessId " +
                " join fetch wfpm.wfBaseProcessId " +
                " order by wfpm.startDateTime desc ")
})
@Entity
@Table(name = "wf_process_movement", indexes = {
        @Index(name = "wfpm_idx_wfuserid", columnList = "user_id"),
        @Index(name = "wfpm_idx_wfpackageid", columnList = "wf_package_id"),
        @Index(name = "wfpm_idx_wfstateid", columnList = "wf_state_id"),
        @Index(name = "wfpm_idx_wfprocessid", columnList = "wf_process_id"),
        @Index(name = "wfpm_idx_wfbaseprocessid", columnList = "wf_base_process_id")
})
public class WFProcessMovement extends WFAbstractBaseEntity {

    public static final String DELETE = "WFProcessMovement.delete";
    public static final String GET = "WFProcessMovement.get";
    public static final String ALL_SORTED = "WFProcessMovement.allSorted";

    public static final boolean NOT_COMPLETED = false;
    public static final boolean IS_COMPLETED = true;
    public static final boolean IS_LAST = true;
    public static final boolean NOT_LAST = false;

    @NotNull
    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;
    @Column(name = "final_date_time")
    private LocalDateTime finalDateTime;
    @NotNull
    @Column(name = "is_completed", nullable = false, columnDefinition = "bool default true")
    private boolean isCompleted;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "date_edit", nullable = false)
    private LocalDateTime dateEdit;
    @NotBlank
    @Column(name = "user_edit", nullable = false)
    private String userEdit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_package_id", nullable = false)
    private WFPackage wfPackageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_state_id", nullable = false)
    private WFProcessState wfStateId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_process_id", nullable = false)
    private WFProcess wfProcessId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_base_process_id", nullable = false)
    private WFBaseProcess wfBaseProcessId;
    @NotNull
    @Column(name = "is_last", nullable = false)
    private boolean isLast;

    public WFProcessMovement() {
    }

    public WFProcessMovement(Integer id, LocalDateTime startDateTime, LocalDateTime finalDateTime, boolean isCompleted, String description, LocalDateTime dateEdit, String userEdit, User userId, WFPackage wfPackageId, WFProcessState wfStateId, WFProcess wfProcessId, WFBaseProcess wfBaseProcessId, boolean isLast) {
        super(id);
        this.startDateTime = startDateTime;
        this.finalDateTime = finalDateTime;
        this.isCompleted = isCompleted;
        this.description = description;
        this.dateEdit = dateEdit;
        this.userEdit = userEdit;
        this.userId = userId;
        this.wfPackageId = wfPackageId;
        this.wfStateId = wfStateId;
        this.wfProcessId = wfProcessId;
        this.wfBaseProcessId = wfBaseProcessId;
        this.isLast = isLast;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getFinalDateTime() {
        return finalDateTime;
    }

    public void setFinalDateTime(LocalDateTime finalDateTime) {
        this.finalDateTime = finalDateTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public WFPackage getWfPackageId() {
        return wfPackageId;
    }

    public void setWfPackageId(WFPackage wfPackageId) {
        this.wfPackageId = wfPackageId;
    }

    public WFProcessState getWfStateId() {
        return wfStateId;
    }

    public void setWfStateId(WFProcessState wfStateId) {
        this.wfStateId = wfStateId;
    }

    public WFProcess getWfProcessId() {
        return wfProcessId;
    }

    public void setWfProcessId(WFProcess wfProcessId) {
        this.wfProcessId = wfProcessId;
    }

    public WFBaseProcess getWfBaseProcessId() {
        return wfBaseProcessId;
    }

    public void setWfBaseProcessId(WFBaseProcess wfBaseProcessId) {
        this.wfBaseProcessId = wfBaseProcessId;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    @Override
    public String toString() {
        return "WFProcessMovement{" +
                "startDateTime=" + startDateTime +
                ", finalDateTime=" + finalDateTime +
                ", isCompleted=" + isCompleted +
                ", description='" + description + '\'' +
                ", dateEdit=" + dateEdit +
                ", userEdit='" + userEdit + '\'' +
                ", userId=" + userId +
                ", wfPackageId=" + wfPackageId +
                ", wfStateId=" + wfStateId +
                ", wfProcessId=" + wfProcessId +
                ", wfBaseProcessId=" + wfBaseProcessId +
                ", isLast=" + isLast +
                ", id=" + id +
                '}';
    }
}
