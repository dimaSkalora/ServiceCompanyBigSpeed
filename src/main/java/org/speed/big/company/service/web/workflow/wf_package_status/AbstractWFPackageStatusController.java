package org.speed.big.company.service.web.workflow.wf_package_status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.service.workflow.WFPackageStatusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractWFPackageStatusController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFPackageStatusService wfPackageStatusService;

    public WFPackageStatus create(WFPackageStatus wfPackageStatus){
        checkNew(wfPackageStatus);
        log.info("create {}",wfPackageStatus);
        return wfPackageStatusService.create(wfPackageStatus);
    }

    public WFPackageStatus update(WFPackageStatus wfPackageStatus){
        checkNotNew(wfPackageStatus);
        log.info("update {}",wfPackageStatus);
        return wfPackageStatusService.update(wfPackageStatus);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfPackageStatusService.delete(id);
    }

    public WFPackageStatus get(int id){
        log.info("get {}",id);
        return wfPackageStatusService.get(id);
    }

    public List<WFPackageStatus> getAll(){
        log.info("getAll");
        return wfPackageStatusService.getAll();
    }

    public List<WFPackageStatus> filter(WFPackageStatus wfPackageStatus){
        log.info("filter {}",wfPackageStatus);
        return wfPackageStatusService.filter(wfPackageStatus);
    }
}
