package org.speed.big.company.service.web.workflow.wf_base_process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.service.workflow.WFBaseProcessService;
import org.speed.big.company.service.service.workflow.WFBaseProcessTypeService;
import org.speed.big.company.service.service.workflow.WFServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractWFBaseProcessController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFBaseProcessService wfBaseProcessService;
    @Autowired
    private WFServiceService wfServiceService;
    @Autowired
    private WFBaseProcessTypeService wfBaseProcessTypeService;

    public WFBaseProcess create(WFBaseProcess wfBaseProcess){
        checkNew(wfBaseProcess);
        log.info("create {}",wfBaseProcess);
        return wfBaseProcessService.create(wfBaseProcess);
    }

    public WFBaseProcess update(WFBaseProcess wfBaseProcess){
        checkNotNew(wfBaseProcess);
        log.info("update {}",wfBaseProcess);
        return wfBaseProcessService.update(wfBaseProcess);
    }

    public WFBaseProcess get(int id){
        log.info("get {}",id);
        return wfBaseProcessService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfBaseProcessService.delete(id);
    }

    public List<WFBaseProcess> getAll(){
        log.info("getAll");
        return wfBaseProcessService.getAll();
    }

    public List<WFBaseProcess> filter(WFBaseProcess wfBaseProcess){
        log.info("filter {}",wfBaseProcess);
        return wfBaseProcessService.filter(wfBaseProcess);
    }

    public WFService getWFS(int wfServiceId){
        log.info("getWFS {}",wfServiceId);
        return wfServiceService.get(wfServiceId);
    }

    public List<WFService> getAllWFS(){
        log.info("getAllWFS");
        return wfServiceService.getAll();
    }

    public WFBaseProcessType getWFBPT(int wfBaseProcessTypeId){
        log.info("getWFBPT {}",wfBaseProcessTypeId);
        return wfBaseProcessTypeService.get(wfBaseProcessTypeId);
    }

    public List<WFBaseProcessType> getAllWFBPT(){
        log.info("getAllWFBPT");
        return wfBaseProcessTypeService.getAll();
    }
}
