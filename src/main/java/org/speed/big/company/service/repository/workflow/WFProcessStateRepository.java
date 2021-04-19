package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFProcessState;

import java.util.List;

public interface WFProcessStateRepository {
    WFProcessState save(WFProcessState wfProcessState);
    WFProcessState get(int id);
    boolean delete(int id);
    List<WFProcessState> getAll();
    List<WFProcessState> filter(WFProcessState wfProcessState);
    List<WFProcessState> getByRoleId(int roleId);
}
