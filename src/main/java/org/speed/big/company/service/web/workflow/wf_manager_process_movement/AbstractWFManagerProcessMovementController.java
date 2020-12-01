package org.speed.big.company.service.web.workflow.wf_manager_process_movement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.*;
import org.speed.big.company.service.service.RoleService;
import org.speed.big.company.service.service.UserRoleService;
import org.speed.big.company.service.service.UserService;
import org.speed.big.company.service.service.workflow.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractWFManagerProcessMovementController {

    private Logger log = LoggerFactory.getLogger(AbstractWFManagerProcessMovementController.class);

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

    /**
     * Получить доступные роли которые есть у юзера
     * @return - список ролей
     */
    public List<Role> getRoleFromUserRoles(){
        int userId=100;//admin

        List<Role> roles = roleService.getRoleFromUserRoleByUser(userId).stream()
                .filter(role -> role.getRoleTypeId().getId() == 1)
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

        List<Role> roles = roleService.getRoleFromUserRoleByUserRoleType(userId,2);

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
                .filter(ur -> ur.getRoleId().getRoleTypeId().getId()==1)
                .map(ur -> ur.getRoleId())
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
                .filter(ur -> ur.getUserId().getId()==userId)
                .filter(ur -> ur.getRoleId().getRoleTypeId().getId()==2)
                .map(ur -> ur.getRoleId())
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
     * Получаем Id тексущего состояние процесса по id пакета
     *
     * @param wfPackageId      -   Id дпакета
     * @return
     */
    public int currentStateWFPMByWFPackageId(int wfPackageId){
        int currentWfProcessState = wfProcessMovementService.get(wfPackageId).getWfStateId().getId();

        log.info("currentWfProcessState {}",currentWfProcessState);

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

}
