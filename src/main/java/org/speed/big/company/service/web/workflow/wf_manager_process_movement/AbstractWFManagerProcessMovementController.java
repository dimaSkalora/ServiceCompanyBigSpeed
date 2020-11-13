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

    public List<Role> getRoleFromUserRoles(){
        int userId=100;//admin

        List<Role> roles = roleService.getRoleFromUserRoleByUser(userId).stream()
                .filter(role -> role.getRoleTypeId().getId() == 1)
                .collect(Collectors.toList());

        return roles;
    }

    public List<WFService> getWFServiceFromRoles(){
        int userId=100;//admin

        List<Role> roles = roleService.getRoleFromUserRoleByUserRoleType(userId,2);

        List<WFService> wfServices = wfServiceService.getWFServiceFromRoles(roles);

        return wfServices;
    }

    public List<Role> getRoleFromUserRolesViaStream(){
        int userId=100;//admin

        List<Role> roles = userRoleService.getAll()
                .stream().filter(ur -> ur.getUserId().getId()==userId)
                .filter(ur -> ur.getRoleId().getRoleTypeId().getId()==1)
                .map(ur -> ur.getRoleId())
                .collect(Collectors.toList());

        return roles;
    }

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

    public List<WFProcessMovement> getListWFProcessMovement(int roleId, int wfServiceId, int indexList){
        List<WFProcessMovement> list = wfProcessMovementService.getAll();

        return list;
    }



}
