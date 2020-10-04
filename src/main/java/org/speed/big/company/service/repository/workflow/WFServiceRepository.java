package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFService;

import java.util.List;

public interface WFServiceRepository {
    WFService save(WFService wfService);
    WFService get(int id);
    boolean delete(int id);
    List<WFService> getAll();
    List<WFService> filter(WFService wfService);
}
