package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = WFBaseProcessItem.DELETE, query = "delete from WFBaseProcessItem where id=:id"),
        @NamedQuery(name = WFBaseProcessItem.GET, query = "select wfbpi from WFBaseProcessItem wfbpi where wfbpi.id=:id"),
        @NamedQuery(name = WFBaseProcessItem.ALL_SORTED, query = "select wfbpi from WFBaseProcessItem wfbpi " +
                " order by wfbpi.baseProcessId")
})
@Entity
@Table(name = "wf_base_process_items",uniqueConstraints = @UniqueConstraint(name = "wfbpi_sfid_stid_bpid_unique_idx",
        columnNames = {"state_from_id","state_to_id","base_process_id"}), indexes = {
        @Index(name = "wfbpi_idx_statefromid", columnList = "state_from_id"),
        @Index(name = "wfbpi_idx_statetoid", columnList = "state_to_id"),
        @Index(name = "wfbpi_idx_baseprocessid", columnList = "base_process_id")
})
public class WFBaseProcessItem extends AbstractBaseEntity {

    public static final String DELETE = "WFBaseProcessItem.delete";
    public static final String GET = "WFBaseProcessItem.get";
    public static final String ALL_SORTED = "WFBaseProcessItem.allSorted";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_from_id")
    private WFProcessState stateFromId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_to_id", nullable = false)
    private WFProcessState stateToId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_process_id", nullable = false)
    private WFBaseProcess baseProcessId;

    public WFBaseProcessItem() {
    }

    public WFBaseProcessItem(Integer id, WFProcessState stateFromId, WFProcessState stateToId, WFBaseProcess baseProcessId) {
        super(id);
        this.stateFromId = stateFromId;
        this.stateToId = stateToId;
        this.baseProcessId = baseProcessId;
    }

    public WFProcessState getStateFromId() {
        return stateFromId;
    }

    public void setStateFromId(WFProcessState stateFromId) {
        this.stateFromId = stateFromId;
    }

    public WFProcessState getStateToId() {
        return stateToId;
    }

    public void setStateToId(WFProcessState stateToId) {
        this.stateToId = stateToId;
    }

    public WFBaseProcess getBaseProcessId() {
        return baseProcessId;
    }

    public void setBaseProcessId(WFBaseProcess baseProcessId) {
        this.baseProcessId = baseProcessId;
    }

    @Override
    public String toString() {
        return "WFBaseProcessItem{" +
                "stateFromId=" + stateFromId +
                ", stateToId=" + stateToId +
                ", baseProcessId=" + baseProcessId +
                ", id=" + id +
                '}';
    }
}
