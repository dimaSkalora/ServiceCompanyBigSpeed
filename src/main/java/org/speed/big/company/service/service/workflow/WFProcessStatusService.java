package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFProcessStatusService {
    WFProcessStatus create(WFProcessStatus wfProcessStatus);
    WFProcessStatus update(WFProcessStatus wfProcessStatus)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFProcessStatus get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFProcessStatus> getAll();
    List<WFProcessStatus> filter(WFProcessStatus wfProcessStatus);
}
