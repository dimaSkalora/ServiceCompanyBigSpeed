package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;

import java.util.List;

public interface WFBaseProcessTypeRepository {
    WFBaseProcessType save(WFBaseProcessType wfBaseProcessType);
    WFBaseProcessType get(int id);
    boolean delete(int id);
    List<WFBaseProcessType> getAll();
    List<WFBaseProcessType> filter(WFBaseProcessType wfBaseProcessType);
}
