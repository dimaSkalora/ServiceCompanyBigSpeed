package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFProcessMovement;

import java.util.List;

public interface WFProcessMovementRepository {
    WFProcessMovement save(WFProcessMovement wfProcessMovement);
    WFProcessMovement get(int id);
    boolean delete(int id);
    List<WFProcessMovement> getAll();
    List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement);
    List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement, String sqlCondition);
}
