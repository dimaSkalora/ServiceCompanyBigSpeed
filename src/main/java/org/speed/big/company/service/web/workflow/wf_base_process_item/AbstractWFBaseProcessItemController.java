package org.speed.big.company.service.web.workflow.wf_base_process_item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.service.workflow.WFBaseProcessItemService;
import org.speed.big.company.service.service.workflow.WFBaseProcessService;
import org.speed.big.company.service.service.workflow.WFProcessStateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.*;

public abstract class AbstractWFBaseProcessItemController {
    private Logger log = LoggerFactory.getLogger(AbstractWFBaseProcessItemController.class);

    @Autowired
    private WFBaseProcessItemService wfBaseProcessItemService;
    @Autowired
    private WFProcessStateService wfProcessStateService;
    @Autowired
    private WFBaseProcessService wfBaseProcessService;

    public WFBaseProcessItem create(WFBaseProcessItem wfBaseProcessItem){
        checkNew(wfBaseProcessItem);
        log.info("create {}",wfBaseProcessItem);
        return wfBaseProcessItemService.create(wfBaseProcessItem);
    }

    public WFBaseProcessItem update(WFBaseProcessItem wfBaseProcessItem, int id){
        assureIdConsistent(wfBaseProcessItem, id);
        log.info("update {}",wfBaseProcessItem);
        return wfBaseProcessItemService.update(wfBaseProcessItem);
    }

    public WFBaseProcessItem get(int id){
        log.info("get {}",id);
        return wfBaseProcessItemService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfBaseProcessItemService.delete(id);
    }

    public List<WFBaseProcessItem> getAll(){
        log.info("getAll");
        return wfBaseProcessItemService.getAll();
    }

    public List<WFBaseProcessItem> filter(WFBaseProcessItem wfBaseProcessItem){
        log.info("filter {}",wfBaseProcessItem);
        return wfBaseProcessItemService.filter(wfBaseProcessItem);
    }

    public WFProcessState getWFProcessState(int wfProcessStateId){
        log.info("getWFProcessState {}",wfProcessStateId);
        return wfProcessStateService.get(wfProcessStateId);
    }

    public List<WFProcessState> getAllWFProcessStates(){
        log.info("getAllWFProcessState");
        return wfProcessStateService.getAll();
    }

    public WFBaseProcess getWFBaseProcess(int wfBaseProcessId){
        log.info("getWFBaseProcess",wfBaseProcessId);
        return wfBaseProcessService.get(wfBaseProcessId);
    }

    public List<WFBaseProcess> getAllWFBaseProcesses(){
        log.info("getAllWFBaseProcess");
        return wfBaseProcessService.getAll();
    }

}
