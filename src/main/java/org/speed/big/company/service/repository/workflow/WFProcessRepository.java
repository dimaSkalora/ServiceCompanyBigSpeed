package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFProcess;

import java.util.List;

public interface WFProcessRepository {
    WFProcess save(WFProcess wfProcess);
    WFProcess get(int id);
    boolean delete(int id);
    List<WFProcess> getAll();
    List<WFProcess> filter(WFProcess wfProcess);
    List<WFProcess> filter(WFProcess wfProcess, String sqlCondition);
}
