package org.speed.big.company.service.web.workflow.wf_process_status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.service.workflow.WFProcessStatusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractWFProcessStatusController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFProcessStatusService wfProcessStatusService;

    public WFProcessStatus create(WFProcessStatus wfProcessStatus){
        checkNew(wfProcessStatus);
        log.info("create {}",wfProcessStatus);
        return wfProcessStatusService.create(wfProcessStatus);
    }

    public WFProcessStatus update(WFProcessStatus wfProcessStatus){
        checkNotNew(wfProcessStatus);
        log.info("update {}",wfProcessStatus);
        return wfProcessStatusService.update(wfProcessStatus);
    }

    public WFProcessStatus get(int id){
        log.info("get {}",id);
        return wfProcessStatusService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfProcessStatusService.delete(id);
    }

    public List<WFProcessStatus> getAll(){
        log.info("getAll");
        return wfProcessStatusService.getAll();
    }

    public List<WFProcessStatus> filter(WFProcessStatus wfProcessStatus){
        log.info("filter",wfProcessStatus);
        return wfProcessStatusService.filter(wfProcessStatus);
    }

}
