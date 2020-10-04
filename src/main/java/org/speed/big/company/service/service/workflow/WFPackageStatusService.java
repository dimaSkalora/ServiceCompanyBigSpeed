package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFPackageStatusService {
    WFPackageStatus create(WFPackageStatus wfPackageStatus);
    WFPackageStatus update(WFPackageStatus wfPackageStatus)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFPackageStatus get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<WFPackageStatus> getAll();
    List<WFPackageStatus> filter(WFPackageStatus wfPackageStatus);
}
