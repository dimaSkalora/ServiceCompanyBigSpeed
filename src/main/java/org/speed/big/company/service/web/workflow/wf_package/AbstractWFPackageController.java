package org.speed.big.company.service.web.workflow.wf_package;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.service.workflow.WFPackageService;
import org.speed.big.company.service.service.workflow.WFPackageStatusService;
import org.speed.big.company.service.service.workflow.WFServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.*;

public abstract class AbstractWFPackageController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFPackageService wfPackageService;
    @Autowired
    private WFPackageStatusService wfPackageStatusService;
    @Autowired
    private WFServiceService wfServiceService;

    public WFPackage create(WFPackage wfPackage){
        checkNew(wfPackage);
        log.info("create {}",wfPackage);

        return wfPackageService.create(wfPackage);
    }

    public WFPackage update(WFPackage wfPackage, int id){
        assureIdConsistent(wfPackage, id);
        log.info("update {}", wfPackage);

        return wfPackageService.update(wfPackage);
    }

    public WFPackage get(int id){
        log.info("get {}", id);
        return wfPackageService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}", id);
        return wfPackageService.delete(id);
    }

    public List<WFPackage> getAll(){
        log.info("getAll");
        return wfPackageService.getAll();
    }

    public List<WFPackage> filter(WFPackage wfPackage){
        log.info("filter {}", wfPackage);
        return wfPackageService.filter(wfPackage);
    }

    public WFPackageStatus getWFPS(int id){
        log.info("getWPS {}",id);
        return wfPackageStatusService.get(id);
    }

    public List<WFPackageStatus> getAllWFPS(){
        log.info("getAllWFPS");
        return wfPackageStatusService.getAll();
    }

    public WFService getWFS(int id){
        log.info("getWFS {}",id);
        return wfServiceService.get(id);
    }

    public List<WFService> getAllWFS(){
        log.info("getAllWFS");
        return wfServiceService.getAll();
    }
}
