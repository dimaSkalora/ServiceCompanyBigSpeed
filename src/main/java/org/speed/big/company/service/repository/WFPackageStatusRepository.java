package org.speed.big.company.service.repository;

import org.speed.big.company.service.model.workflow.WFPackageStatus;

import java.util.List;

public interface WFPackageStatusRepository {
    WFPackageStatus save(WFPackageStatus wfPackageStatus);
    WFPackageStatus get(int id);
    boolean delete(int id);
    List<WFPackageStatus> getAll();
    List<WFPackageStatus> filter(WFPackageStatus wfPackageStatus);
}
