package org.speed.big.company.service.web.workflow.wf_process_movement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.workflow.*;
import org.speed.big.company.service.service.UserService;
import org.speed.big.company.service.service.workflow.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractWFProcessMovementController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WFProcessMovementService wfProcessMovementService;
    @Autowired
    private UserService userService;
    @Autowired
    private WFPackageService wfPackageService;
    @Autowired
    private WFProcessStateService wfProcessStateService;
    @Autowired
    private WFProcessService wfProcessService;
    @Autowired
    private WFBaseProcessService wfBaseProcessService;

    public WFProcessMovement create(WFProcessMovement wfProcessMovement){
        checkNew(wfProcessMovement);
        log.info("create {}",wfProcessMovement);
        return wfProcessMovementService.create(wfProcessMovement);
    }

    public WFProcessMovement update(WFProcessMovement wfProcessMovement){
        checkNotNew(wfProcessMovement);
        log.info("update {}",wfProcessMovement);
        return wfProcessMovementService.update(wfProcessMovement);
    }

    public WFProcessMovement get(int id){
        log.info("get {}",id);
        return wfProcessMovementService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return wfProcessMovementService.delete(id);
    }

    public List<WFProcessMovement> getAll(){
        log.info("getAll");
        return wfProcessMovementService.getAll();
    }

    public List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement){
        log.info("filter {}",wfProcessMovement);
        return wfProcessMovementService.filter(wfProcessMovement);
    }

    public List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement, String sqlCondition){
        log.info("filter {}, sqlCondition {}",wfProcessMovement,sqlCondition);
        return wfProcessMovementService.filter(wfProcessMovement,sqlCondition);
    }

    public User getUser(int userId){
        User user = userService.get(userId);
        log.info("getUser {}",userId);
        return user;
    }

    public List<User> getAllUsers(){
        log.info("getAllUser");
        return userService.getAll().stream()
                .filter(u->u.isEnabled() == true)
                .collect(Collectors.toList());
    }

    public WFPackage getWFPackage(int wfPackageId){
        WFPackage wfPackage = wfPackageService.get(wfPackageId);
        log.info("getWFPackage {}",wfPackageId);
        return wfPackage;
    }

    public List<WFPackage> getAllWFPackages(){
        log.info("getAllWFPackages");
        return wfPackageService.getAll();
    }

    public WFProcessState getWFProcessState(int wfProcessStateId){
        WFProcessState wfProcessState = wfProcessStateService.get(wfProcessStateId);
        log.info("getWFProcessState {}",wfProcessStateId);
        return wfProcessState;
    }

    public List<WFProcessState> getAllWFProcessStates(){
        List<WFProcessState> wfProcessStates = wfProcessStateService.getAll();
        log.info("getAllWFProcessStates {}");
        return wfProcessStates;
    }

    public WFProcess getWFProcess(int wfProcessId){
        WFProcess wfProcess = wfProcessService.get(wfProcessId);
        log.info("getWFProcess {}",wfProcessId);
        return wfProcess;
    }

    public List<WFProcess> getAllWFProcesses(){
        log.info("getAllWFProcesses");
        return wfProcessService.getAll();
    }

    public WFBaseProcess getWFBaseProcess(int wfBaseProcessId){
        WFBaseProcess wfBaseProcess = wfBaseProcessService.get(wfBaseProcessId);
        log.info("getWFBaseProcess",wfBaseProcessId);
        return wfBaseProcess;
    }

    public List<WFBaseProcess> getAllWFBaseProcesses(){
        log.info("getAllWFBaseProcesses");
        return wfBaseProcessService.getAll();
    }

}
