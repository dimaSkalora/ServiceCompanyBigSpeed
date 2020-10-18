package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFGroupService {
    WFGroup create(WFGroup wfGroup);
    WFGroup update(WFGroup wfGroup)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFGroup get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFGroup> getAll();
    List<WFGroup> filter(WFGroup wfGroup);
}
