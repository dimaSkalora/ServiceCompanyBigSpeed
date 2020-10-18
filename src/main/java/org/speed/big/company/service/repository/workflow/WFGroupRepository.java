package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFGroup;

import java.util.List;

public interface WFGroupRepository {
    WFGroup save(WFGroup wfGroup);
    WFGroup get(int id);
    boolean delete(int id);
    List<WFGroup> getAll();
    List<WFGroup> filter(WFGroup wfGroup);
}
