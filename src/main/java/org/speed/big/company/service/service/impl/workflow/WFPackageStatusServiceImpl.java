package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.service.workflow.WFPackageStatusService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("wfPackageStatusServiceImpl")
public class WFPackageStatusServiceImpl implements WFPackageStatusService {

    private WFPackageStatusService wfPackageStatusService;

    @Autowired
    public WFPackageStatusServiceImpl(@Qualifier("jdbcWFPackageStatusRepositoryImpl")WFPackageStatusService wfPackageStatusService) {
        this.wfPackageStatusService = wfPackageStatusService;
    }

    @Override
    public WFPackageStatus save(WFPackageStatus wfPackageStatus) {
        Assert.notNull(wfPackageStatus,"не должно быть null");
        return wfPackageStatusService.save(wfPackageStatus);
    }

    @Override
    public WFPackageStatus update(WFPackageStatus wfPackageStatus) throws NotFoundException {
        return null;
    }

    @Override
    public WFPackageStatus get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return false;
    }

    @Override
    public List<WFPackageStatus> getAll() {
        return null;
    }

    @Override
    public List<WFPackageStatus> filter(WFPackageStatus wfPackageStatus) {
        return null;
    }
}
