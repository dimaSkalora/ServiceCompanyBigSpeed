package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFProcessMovementService {
    WFProcessMovement create(WFProcessMovement wfProcessMovement);
    WFProcessMovement update(WFProcessMovement wfProcessMovement)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFProcessMovement get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFProcessMovement> getAll();
    List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement);
    List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement, String sqlCondition);
    List<WFProcessMovement> getListWFProcessMovement(int roleId, int wfServiceId, int processStatus, boolean isCompleted, boolean isLast);
    List<WFProcessMovement> getListWFPMByProcessAndBaseProcess(int wfProcessId, int wfBaseProcessId);
    int currentStateIdOfWFProcessMovementById(int id);
    int currentStateIdOfWFProcessMovement(int wfPackageId, int wfProcessId, int wfBaseProcessId);
}
