package org.speed.big.company.service.web.workflow.wf_base_process_type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.service.workflow.WFBaseProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractWFBaseProcessTypeController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFBaseProcessTypeService wfBaseProcessTypeService;

    public WFBaseProcessType create(WFBaseProcessType wfBaseProcessType){
        checkNew(wfBaseProcessType);
        log.info("create {}",wfBaseProcessType);

        return wfBaseProcessTypeService.create(wfBaseProcessType);
    }

    public WFBaseProcessType update(WFBaseProcessType wfBaseProcessType){
        checkNotNew(wfBaseProcessType);
        log.info("update {}",wfBaseProcessType);

        return wfBaseProcessTypeService.update(wfBaseProcessType);
    }

    public WFBaseProcessType get(int id){
        log.info("get {}",id);
        return wfBaseProcessTypeService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfBaseProcessTypeService.delete(id);
    }

    public List<WFBaseProcessType> getAll(){
        log.info("getAll");
        return wfBaseProcessTypeService.getAll();
    }

    public List<WFBaseProcessType> filter(WFBaseProcessType wfBaseProcessType){
        log.info("filter {}",wfBaseProcessType);
        return wfBaseProcessTypeService.filter(wfBaseProcessType);
    }
}
