package org.speed.big.company.service.web.workflow.wf_process_state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.service.RoleService;
import org.speed.big.company.service.service.workflow.WFGroupService;
import org.speed.big.company.service.service.workflow.WFProcessStateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractWFProcessStateController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFProcessStateService wfProcessStateService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private WFGroupService wfGroupService;

    public WFProcessState create(WFProcessState wfProcessState){
        checkNew(wfProcessState);
        log.info("create {}",wfProcessState);
        return wfProcessStateService.create(wfProcessState);
    }

    public WFProcessState update(WFProcessState wfProcessState){
        checkNotNew(wfProcessState);
        log.info("update {}",wfProcessState);
        return wfProcessStateService.update(wfProcessState);
    }

    public WFProcessState get(int id){
        log.info("get {}", id);
        return wfProcessStateService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfProcessStateService.delete(id);
    }

    public List<WFProcessState> getAll(){
        log.info("getAll()");
        return wfProcessStateService.getAll();
    }

    public List<WFProcessState> filter(WFProcessState wfProcessState){
        log.info("filter {}",wfProcessState);
        return wfProcessStateService.filter(wfProcessState);
    }

    public Role getRole(int roleId){
        log.info("getRole {}",roleId);
        return roleService.get(roleId);
    }

    public List<Role> getAllRoles(){
        log.info("getAllRoles");
        List<Role> listWF = roleService.getAll().stream()
                .filter(role -> role.getRoleTypeId().getId()==RoleType.WEB_WORKFLOW)
                .collect(Collectors.toList());

        return listWF;
    }

    public WFGroup getWFGroup(int groupId){
        log.info("getWFGroup {}",groupId);
        return wfGroupService.get(groupId);
    }

    public List<WFGroup> getAllWFGroup(){
        log.info("getAllWFGroup");
        return wfGroupService.getAll();
    }
}
