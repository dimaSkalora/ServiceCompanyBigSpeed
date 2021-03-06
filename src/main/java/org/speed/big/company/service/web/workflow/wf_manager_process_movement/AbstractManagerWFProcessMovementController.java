package org.speed.big.company.service.web.workflow.wf_manager_process_movement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.workflow.*;
import org.speed.big.company.service.service.RoleService;
import org.speed.big.company.service.service.UserRoleService;
import org.speed.big.company.service.service.UserService;
import org.speed.big.company.service.service.workflow.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractManagerWFProcessMovementController {

    private Logger log = LoggerFactory.getLogger(AbstractManagerWFProcessMovementController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private WFServiceService wfServiceService;
    @Autowired
    private WFProcessStatusService wfProcessStatusService;
    @Autowired
    private WFProcessMovementService wfProcessMovementService;
    @Autowired
    private WFProcessService wfProcessService;
    @Autowired
    private WFPackageService wfPackageService;
    @Autowired
    private WFBaseProcessItemService wfBaseProcessItemService;
    @Autowired
    private WFProcessStateService wfProcessStateService;

    /**
     * Получить доступные роли которые есть у юзера
     * @return - список ролей
     */
    public List<Role> getRoleFromUserRoles(){
        int userId=100;//admin

        List<Role> roles = roleService.getRoleFromUserRoleByUser(userId).stream()
                .filter(role -> role.getRoleTypeId().getId() == RoleType.WEB_WORKFLOW)
                .collect(Collectors.toList());

        log.info("getRoleFromUserRoles",roles);

        return roles;
    }

    /**
     * Получить доступные сервисы которые есть у юзера
     * @return - список сервисов
     */
    public List<WFService> getWFServiceFromRoles(){
        int userId=100;//admin

        List<Role> roles = roleService.
                getRoleFromUserRoleByUserRoleType(userId, RoleType.WEB_WORKFLOW);

        List<WFService> wfServices = wfServiceService.getWFServiceFromRoles(roles);

        log.info("getWFServiceFromRoles",wfServices);

        return wfServices;
    }

    /**
     * Получить доступные роли которые есть у юзера(с использованием Stream)
     * @return - список ролей
     */
    public List<Role> getRoleFromUserRolesViaStream(){
        int userId=100;//admin

        List<Role> roles = userRoleService.getAll()
                .stream().filter(ur -> ur.getUserId().getId()==userId)
                .filter(urs -> urs.getRoleId().getRoleTypeId().getId()== RoleType.WEB_WORKFLOW)
                .map(urs -> urs.getRoleId())
                .collect(Collectors.toList());

        log.info("getRoleFromUserRolesViaStream",roles);

        return roles;
    }

    /**
     * Получить доступные сервисы которые есть у юзера(с использованием Stream)
     * @return - список сервисов
     */
    public List<WFService> getWFServiceFromUserRolesViaStream(){
        int userId=100;//admin

        List<Role> roles = userRoleService.getAll().stream()
                .filter(urs -> urs.getUserId().getId()==userId)
                .filter(urs -> urs.getRoleId().getRoleTypeId().getId()== RoleType.WEB_WORKFLOW)
                .map(urs -> urs.getRoleId())
                .collect(Collectors.toList());

        List<WFService> wfServices = wfServiceService.getAll().stream()
                .filter(wfService -> {
                    boolean isName = false;
                    for (Role role: roles){
                        if (wfService.equals(role.getName())){
                            isName = true;
                            return isName;
                        }
                    }
                    return isName;
                })
                .collect(Collectors.toList());

        log.info("getWFServiceFromUserRolesViaStream",wfServices);

        return wfServices;
    }

    /**
     * Получить список движений (ролям, сервисам)
     * @param roleId            -   Роль
     * @param wfServiceId       -   Сервис
     * @param indexList         -   Номер индекса (входящие, ожыдание, ...)
     * @return
     */
    public List<WFProcessMovement> getListWFProcessMovement(int roleId, int wfServiceId, int indexList){
        List<WFProcessMovement> list = null;

        switch (indexList){
            case 1 ->  list = wfProcessMovementService.getListWFProcessMovement(roleId,wfServiceId,
                    WFProcessStatus.IN_WORK,WFProcessMovement.NOT_COMPLETED,WFProcessMovement.IS_LAST);
            case 3 -> list = wfProcessMovementService.getListWFProcessMovement(roleId,wfServiceId,
                    WFProcessStatus.WAITING,WFProcessMovement.NOT_COMPLETED,WFProcessMovement.IS_LAST);
        }

        log.info("getListWFProcessMovement",list);

        return list;
    }

    /**
     * Получить список движений (сервисам)
     * @param wfServiceId       -   Сервис
     * @param indexList         -   Номер индекса (завершонные, архив, ...)
     * @return
     */
    public List<WFProcess> getListWFProcess(int wfServiceId, int indexList){
        List<WFProcess> list = null;

        switch (indexList){
            case 2 -> list = wfProcessService.getListWFProcess(wfServiceId,WFProcessStatus.COMPLETED);
            case 4 -> list = wfProcessService.getListWFProcess(wfServiceId,WFProcessStatus.ARCHIVE);
        }

        log.info("getListWFProcess",list);

        return list;
    }

    /**
     * Получаем пакет по текущему двежению процесса
     *
     * @param wfPackId      -   Id Пакета
     * @return
     */
    public WFPackage getWFPackageOfProcessMovement(int wfPackId){
        WFPackage wfPackage = wfPackageService.get(wfPackId);

        log.info("getWFPackageOfProcessMovement",wfPackage);

        return wfPackage;
    }

    /**
     * Получаем Id состояние процесса по текущему движению
     *
     * @param id      -   id
     * @return
     */
    public int currentStateWFProcessMovementById(int id){
        int currentWfProcessState = wfProcessMovementService.currentStateIdOfWFProcessMovementById(id);

        log.info("currentStateWFProcessMovementById {}",currentWfProcessState);

        return currentWfProcessState;
    }

    /**
     * Получить все процессы пакета
     * @param wfPackageId
     * @return
     */
    public List<WFProcess> getAllWFProcessByWFPackageId(int wfPackageId){
        List<WFProcess> list = wfProcessService.getByWFPackageId(wfPackageId);

        log.info("getAllWFProcessByWFPackageId {}",wfPackageId);

        return list;
    }

    /**
     *  Получить процесс текущего пакета
     * @param id - id процесса
     * @return
     */
    public WFProcess getWFProcessById(int id){
        WFProcess wfProcess = wfProcessService.get(id);

        log.info("getWFProcessById {}",id);

        return wfProcess;
    }

    /**
     *
     * Получить список движений по id процессу и id базового процесса
     *
     * @param wfProcessId
     * @param wfBaseProcessId
     * @return
     */
    public List<WFProcessMovement> getListWFPMByProcessAndBaseProcess(int wfProcessId, int wfBaseProcessId){
        List<WFProcessMovement> list = wfProcessMovementService.getListWFPMByProcessAndBaseProcess(wfProcessId,wfBaseProcessId);

        log.info("getListWFPMByProcessAndBaseProcess: wfProcessId - {}, wfBaseProcessId - {}",wfProcessId,wfBaseProcessId);

        return list;
    }

    /**
     * Получить список состояний процесса для передачи задания
     *
     * @param processStateFromId    - Id процесс состояния текущего движение процесса
     * @param baseProcessId         - Id базовый процесс текущего движение процессса
     * @return
     */
    List<WFProcessState> getListTransferWFProcessState(int processStateFromId, int baseProcessId){
        List<WFProcessState> list = wfBaseProcessItemService.getListTransferWFProcessState(processStateFromId,baseProcessId);

        log.info("getListTransferWFProcessState: processStateFromId - {}; baseProcessId - {}",processStateFromId,baseProcessId);

        return list;
    }

    /**
     * Передать задание на другую стадию
     *
     * @param wfProcessMovementId
     * @param processStateToId
     * @param description
     * @return
     */
    WFProcessMovement wfProcessMovementTransferTasks(int wfProcessMovementId, int processStateToId, String description){
        WFProcessMovement wfProcessMovement = wfProcessMovementService.get(wfProcessMovementId);
        WFProcessState wfProcessState = wfProcessStateService.get(processStateToId);

        WFProcessMovement newWFProcessMovement = new WFProcessMovement();
        newWFProcessMovement.setStartDateTime(LocalDateTime.now());
        newWFProcessMovement.setCompleted(WFProcessMovement.NOT_COMPLETED);
        newWFProcessMovement.setDescription(description);
        newWFProcessMovement.setDateEdit(LocalDateTime.now());
        newWFProcessMovement.setUserEdit("test");
        newWFProcessMovement.setUserId(new User());
        newWFProcessMovement.setWfPackageId(wfProcessMovement.getWfPackageId());
        newWFProcessMovement.setWfStateId(wfProcessState);
        newWFProcessMovement.setWfProcessId(wfProcessMovement.getWfProcessId());
        newWFProcessMovement.setWfBaseProcessId(wfProcessMovement.getWfBaseProcessId());
        newWFProcessMovement.setLast(WFProcessMovement.IS_LAST);

        wfProcessMovementService.create(newWFProcessMovement);

        wfProcessMovement.setFinalDateTime(LocalDateTime.now());
        wfProcessMovement.setCompleted(WFProcessMovement.IS_COMPLETED);
        wfProcessMovement.setDateEdit(LocalDateTime.now());
        wfProcessMovement.setUserEdit("test");
        wfProcessMovement.setUserId(new User());
        wfProcessMovement.setLast(WFProcessMovement.NOT_LAST);

        wfProcessMovementService.update(wfProcessMovement);

        return newWFProcessMovement;
    }



}
