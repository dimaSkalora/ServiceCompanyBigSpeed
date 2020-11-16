package org.speed.big.company.service.web.workflow.wf_manager_process_movement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.service.RoleService;
import org.speed.big.company.service.service.UserRoleService;
import org.speed.big.company.service.service.UserService;
import org.speed.big.company.service.service.workflow.WFProcessMovementService;
import org.speed.big.company.service.service.workflow.WFProcessStatusService;
import org.speed.big.company.service.service.workflow.WFServiceService;
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

    /**
     * Получить доступные роли которые есть у юзера
     * @return - список ролей
     */
    public List<Role> getRoleFromUserRoles(){
        int userId=100;//admin

        List<Role> roles = roleService.getRoleFromUserRoleByUser(userId).stream()
                .filter(role -> role.getRoleTypeId().getId() == 1)
                .collect(Collectors.toList());

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

        return wfServices;
    }

    /**
     * Получить список движений (ролям, сервисам)
     * @param roleId            -   Роль
     * @param wfServiceId       -   Сервис
     * @param indexList         -   Номер индекса (входящие, завершонные, ожыдание, архив, ...)
     * @return
     */
    public List<WFProcessMovement> getListWFProcessMovement(int roleId, int wfServiceId, int indexList){
        List<WFProcessMovement> list = null;

        switch (indexList){
            case 1 ->  list = wfProcessMovementService.getListWFProcessMovement(roleId,wfServiceId,
                    WFProcessStatus.IN_WORK,WFProcessMovement.NOT_COMPLETED,WFProcessMovement.IS_LAST);
            case 2 -> list = wfProcessMovementService.getListWFProcessMovement(roleId,wfServiceId,
                    WFProcessStatus.COMPLETED,WFProcessMovement.IS_COMPLETED,WFProcessMovement.IS_LAST);
            case 3 -> list = wfProcessMovementService.getListWFProcessMovement(roleId,wfServiceId,
                    WFProcessStatus.WAITING,WFProcessMovement.NOT_COMPLETED,WFProcessMovement.IS_LAST);
            case 4 -> list = wfProcessMovementService.getListWFProcessMovement(roleId,wfServiceId,
                    WFProcessStatus.ARCHIVE,WFProcessMovement.IS_COMPLETED,WFProcessMovement.IS_LAST);
        }

        return list;
    }



}
