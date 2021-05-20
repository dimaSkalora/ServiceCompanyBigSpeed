package org.speed.big.company.service.web.workflow.wf_group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.service.workflow.WFGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.*;

public abstract class AbstractWFGroupController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFGroupService wfGroupService;

    public WFGroup create(WFGroup wfGroup){
        checkNew(wfGroup);
        log.info("create {}",wfGroup);
        return wfGroupService.create(wfGroup);
    }

    public WFGroup update(WFGroup wfGroup, int id){
        assureIdConsistent(wfGroup, id);
        log.info("update {}",wfGroup);
        return wfGroupService.update(wfGroup);
    }

    public WFGroup get(int id){
        log.info("get {}",id);
        return wfGroupService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfGroupService.delete(id);
    }

    public List<WFGroup> getAll(){
        log.info("getAll");
        return wfGroupService.getAll();
    }

    public List<WFGroup> filter(WFGroup wfGroup){
        log.info("filter {}",wfGroup);
        return wfGroupService.filter(wfGroup);
    }
}
