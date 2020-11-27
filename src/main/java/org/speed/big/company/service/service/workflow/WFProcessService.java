package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFProcessService {
    WFProcess create(WFProcess wfProcess);
    WFProcess update(WFProcess wfProcess)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFProcess get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFProcess> getAll();
    List<WFProcess> filter(WFProcess wfProcess);
    List<WFProcess> filter(WFProcess wfProcess, String sqlCondition);
    List<WFProcess> getListWFProcess(int wfServiceId, int wfProcessStatusId) throws NotFoundException;
    List<WFProcess> getByWFPackageId(int wfPackageId) throws NotFoundException;
}
