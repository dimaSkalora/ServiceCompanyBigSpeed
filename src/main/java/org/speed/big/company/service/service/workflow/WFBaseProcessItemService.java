package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFBaseProcessItemService {
    WFBaseProcessItem create(WFBaseProcessItem wfBaseProcessItem);
    WFBaseProcessItem update(WFBaseProcessItem wfBaseProcessItem)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFBaseProcessItem get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFBaseProcessItem> getAll();
    List<WFBaseProcessItem> filter(WFBaseProcessItem wfBaseProcessItem);
    List<WFProcessState> getListTransferWFProcessState(int processStateFromId, int baseProcessId);
}
