package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFProcessStateService {
    WFProcessState create(WFProcessState wfProcessState);
    WFProcessState update(WFProcessState wfProcessState)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFProcessState get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFProcessState> getAll();
    List<WFProcessState> filter(WFProcessState wfProcessState);
}
