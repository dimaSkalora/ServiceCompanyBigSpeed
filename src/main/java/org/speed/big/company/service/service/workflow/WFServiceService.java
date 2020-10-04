package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFServiceService {
    WFService create(WFService wfService);
    WFService update(WFService wfService)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFService get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFService> getAll();
    List<WFService> filter(WFService wfService);
}
