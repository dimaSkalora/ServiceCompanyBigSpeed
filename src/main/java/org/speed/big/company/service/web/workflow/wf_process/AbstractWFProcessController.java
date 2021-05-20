package org.speed.big.company.service.web.workflow.wf_process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.service.workflow.WFBaseProcessService;
import org.speed.big.company.service.service.workflow.WFPackageService;
import org.speed.big.company.service.service.workflow.WFProcessService;
import org.speed.big.company.service.service.workflow.WFProcessStatusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.*;

public abstract class AbstractWFProcessController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFProcessService wfProcessService;
    @Autowired
    private WFPackageService wfPackageService;
    @Autowired
    private WFBaseProcessService wfBaseProcessService;
    @Autowired
    private WFProcessStatusService wfProcessStatusService;

    public WFProcess create(WFProcess wfProcess){
        checkNew(wfProcess);
        log.info("create {}",wfProcess);
        return wfProcessService.create(wfProcess);
    }

    public WFProcess update(WFProcess wfProcess, int id){
        assureIdConsistent(wfProcess, id);
        log.info("update {}",wfProcess);
        return wfProcessService.update(wfProcess);
    }

    public WFProcess get(int id){
        log.info("get {}",id);
        return wfProcessService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfProcessService.delete(id);
    }

    public List<WFProcess> getAll(){
        log.info("getAll");
        return wfProcessService.getAll();
    }

    public List<WFProcess> filter(WFProcess wfProcess){
        log.info("filter {}",wfProcess);
        return wfProcessService.filter(wfProcess);
    }

    public List<WFProcess> filter(WFProcess wfProcess, String sqlCondition){
        log.info("filter:  wfProcess - {}, sqlCondition - {}",wfProcess,sqlCondition);
        return wfProcessService.filter(wfProcess,sqlCondition);
    }

    public WFPackage getWFPackage(int wfPackageId){
        log.info("getWFPackage {}",wfPackageId);
        return wfPackageService.get(wfPackageId);
    }

    public List<WFPackage> getAllWFPackages(){
        log.info("getAllWFPackages");
        return wfPackageService.getAll();
    }

    public WFBaseProcess getWFBaseProcess(int wfBaseProcessId){
        log.info("getWFBaseProcess {}",wfBaseProcessId);
        return wfBaseProcessService.get(wfBaseProcessId);
    }

    public List<WFBaseProcess> getAllWFBaseProcesses(){
        log.info("getAllWFBaseProcesses");
        return wfBaseProcessService.getAll();
    }

    public WFProcessStatus getWFProcessStatus(int wfProcessStatusId){
        log.info("WFProcessStatus {}",wfProcessStatusId);
        return wfProcessStatusService.get(wfProcessStatusId);
    }

    public List<WFProcessStatus> getAllWFProcessStatuses(){
        log.info("getAllWFProcessStatuses");
        return wfProcessStatusService.getAll();
    }
}
