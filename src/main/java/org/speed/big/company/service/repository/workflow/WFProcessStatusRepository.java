package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFProcessStatus;

import java.util.List;

public interface WFProcessStatusRepository {
    WFProcessStatus save(WFProcessStatus wfProcessStatus);
    WFProcessStatus get(int id);
    boolean delete(int id);
    List<WFProcessStatus> getAll();
    List<WFProcessStatus> filter(WFProcessStatus wfProcessStatus);
}
