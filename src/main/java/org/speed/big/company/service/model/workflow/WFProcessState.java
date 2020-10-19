package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;
import org.speed.big.company.service.model.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NamedQueries({
    @NamedQuery(name = WFProcessStatus.DELETE, query = "delete from WFProcessState where id=:id"),
    @NamedQuery(name = WFProcessStatus.GET, query = "select wfps from WFProcessState wfps where wfps.id=:id"),
    @NamedQuery(name = WFProcessStatus.ALL_SORTED,
            query = "select wfps from WFProcessState wfps order by wfps.name")
})
@Entity
@Table(name = "wf_process_state", indexes = {
        @Index(name = "wfps_idx_wfrolekid", columnList = "role_id"),
        @Index(name = "wfps_idx_wfgroupid", columnList = "group_id")
})
public class WFProcessState extends AbstractBaseEntity {
    public static final String DELETE = "WFProcessState.delete";
    public static final String GET = "WFProcessState.get";
    public static final String ALL_SORTED = "WFProcessState.allSorted";

    @NotBlank
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role roleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private WFGroup groupId;
    private String description;

    public WFProcessState() {
    }

    public WFProcessState(Integer id, String name, Role roleId, WFGroup groupId, String description) {
        super(id);
        this.name = name;
        this.roleId = roleId;
        this.groupId = groupId;
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

    public WFGroup getGroupId() {
        return groupId;
    }

    public void setGroupId(WFGroup groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
