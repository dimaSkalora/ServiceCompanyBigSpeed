package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;

import java.util.List;

public interface WFBaseProcessItemRepository {
    WFBaseProcessItem save(WFBaseProcessItem wfBaseProcessItem);
    WFBaseProcessItem get(int id);
    boolean delete(int id);
    List<WFBaseProcessItem> getAll();
    List<WFBaseProcessItem> filter(WFBaseProcessItem wfBaseProcessItem);
}
