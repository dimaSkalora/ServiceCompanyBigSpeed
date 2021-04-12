package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
    @NamedQuery(name = WFProcessState.DELETE, query = "delete from WFProcessState where id=:id"),
    @NamedQuery(name = WFProcessState.GET, query = "select wfpstate from WFProcessState wfpstate " +
            " join fetch wfpstate.roleId join fetch wfpstate.wfGroupId " +
            " where wfpstate.id=:id"),
    @NamedQuery(name = WFProcessState.ALL_SORTED,
            query = "select wfpstate from WFProcessState wfpstate  " +
                    " join fetch wfpstate.roleId join fetch wfpstate.wfGroupId " +
                    " order by wfpstate.name")
})
@Entity
@Table(name = "wf_process_state", indexes = {
        @Index(name = "wfps_idx_wfrolekid", columnList = "role_id"),
        @Index(name = "wfps_idx_wfgroupid", columnList = "wf_group_id")
})
public class WFProcessState extends WFAbstractBaseEntity {
    public static final String DELETE = "WFProcessState.delete";
    public static final String GET = "WFProcessState.get";
    public static final String ALL_SORTED = "WFProcessState.allSorted";

    @NotBlank
    @Size(min = 5, max = 250)
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role roleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_group_id", nullable = false)
    private WFGroup wfGroupId;
    private String description;

    public WFProcessState() {
    }

    public WFProcessState(Integer id, String name, Role roleId, WFGroup wfGroupId, String description) {
        super(id);
        this.name = name;
        this.roleId = roleId;
        this.wfGroupId = wfGroupId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public WFGroup getWfGroupId() {
        return wfGroupId;
    }

    public void setWfGroupId(WFGroup groupId) {
        this.wfGroupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WFProcessState{" +
                "name='" + name + '\'' +
                ", roleId=" + roleId +
                ", wfGroupId=" + wfGroupId +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
