package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;

import java.util.List;

public interface WFBaseProcessItemRepository {
    WFBaseProcessItem save(WFBaseProcessItem wfBaseProcessItem);
    WFBaseProcessItem get(int id);
    boolean delete(int id);
    List<WFBaseProcessItem> getAll();
    List<WFBaseProcessItem> filter(WFBaseProcessItem wfBaseProcessItem);
    List<WFProcessState> getListTransferWFProcessState(int processStateFromId, int baseProcessId);
}
