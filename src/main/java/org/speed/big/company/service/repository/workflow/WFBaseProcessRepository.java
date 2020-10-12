package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;

import java.util.List;

public interface WFBaseProcessRepository {
    WFBaseProcess save(WFBaseProcess wfBaseProcess);
    WFBaseProcess get(int id);
    boolean delete(int id);
    List<WFBaseProcess> getAll();
    List<WFBaseProcess> filter(WFBaseProcess wfBaseProcess);
}
