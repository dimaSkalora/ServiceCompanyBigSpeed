package org.speed.big.company.service.service.workflow;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface WFPackageService {
    WFPackage save(WFPackage wfPackage);
    WFPackage update(WFPackage wfPackage) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    WFPackage get(int id) throws NotFoundException;
    boolean delete(int id) throws NotFoundException;
    List<WFPackage> getAll();
    List<WFPackage> filter(WFPackage wfPackage);
}
