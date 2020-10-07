package org.speed.big.company.service.repository.workflow;

import org.speed.big.company.service.model.workflow.WFPackage;

import java.util.List;

public interface WFPackageRepository {
    WFPackage save(WFPackage wfPackage);
    WFPackage get(int id);
    boolean delete(int id);
    List<WFPackage> getAll();
    List<WFPackage> filter(WFPackage wfPackage);
    List<WFPackage> filter(WFPackage wfPackage, String sqlCondition);
}
