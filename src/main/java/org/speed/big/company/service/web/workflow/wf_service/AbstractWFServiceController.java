package org.speed.big.company.service.web.workflow.wf_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.service.workflow.WFServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractWFServiceController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFServiceService wfServiceService;

    public WFService create(WFService wfService){
        checkNew(wfService);
        log.info("create {}",wfService);

        return wfServiceService.create(wfService);
    }

    public WFService update(WFService wfService){
        checkNotNew(wfService);
        log.info("update {}",wfService);

        return wfServiceService.update(wfService);
    }

    public WFService get(int id){
        log.info("get {}",id);
        return wfServiceService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfServiceService.delete(id);
    }

    public List<WFService> getAll(){
        log.info("getAll");
        return wfServiceService.getAll();
    }

    public List<WFService> filter(WFService wfService){
        log.info("filter {}", wfService);
        return wfServiceService.filter(wfService);
    }
}
