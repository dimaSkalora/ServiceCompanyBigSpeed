package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFBaseProcessTypeService {
    WFBaseProcessType create(WFBaseProcessType wfBaseProcessType);
    WFBaseProcessType update(WFBaseProcessType wfBaseProcessType)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFBaseProcessType get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFBaseProcessType> getAll();
    List<WFBaseProcessType> filter(WFBaseProcessType wfBaseProcessType);
}
