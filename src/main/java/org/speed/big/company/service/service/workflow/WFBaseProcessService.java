package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFBaseProcessService {
    WFBaseProcess create(WFBaseProcess wfBaseProcess);
    WFBaseProcess update(WFBaseProcess wfBaseProcess)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFBaseProcess get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFBaseProcess> getAll();
    List<WFBaseProcess> filter(WFBaseProcess wfBaseProcess);
}
