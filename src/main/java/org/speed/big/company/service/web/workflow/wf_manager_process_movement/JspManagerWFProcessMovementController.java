package org.speed.big.company.service.web.workflow.wf_manager_process_movement;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("managerWFProcessMovements")
public class JspManagerWFProcessMovementController extends AbstractManagerWFProcessMovementController {

    @GetMapping
    public String managerWFProcessMovements(Model model){
        List<Role> getRoleFromUserRoles = super.getRoleFromUserRoles();
        List<WFService> getWFServiceFromRoles = super.getWFServiceFromRoles();

        model.addAttribute("getRoleFromUserRoles",getRoleFromUserRoles);
        model.addAttribute("getWFServiceFromRoles",getWFServiceFromRoles);

        return "workflow/managerWFProcessMovements/managerWFProcessMovements";
    }

    @GetMapping("/wfProcessMovements/{roleId,}/{wfServiceId}/{indexList}")
    public String wfProcessMovements(@PathVariable int roleId, @PathVariable int wfServiceId,
                                            @PathVariable int indexList, Model model){
        List<WFProcessMovement> wfProcessMovementList = null;
        List<WFProcess> wfProcessList = null;

        if ((indexList == WFProcessStatus.IN_WORK) || (indexList == WFProcessStatus.WAITING))
            wfProcessMovementList = super.getListWFProcessMovement(roleId,wfServiceId,indexList);
        if ((indexList == WFProcessStatus.COMPLETED) || (indexList == WFProcessStatus.ARCHIVE))
            wfProcessList = super.getListWFProcess(wfServiceId,indexList);

        model.addAttribute("managerWFProcessMovements",
                wfProcessMovementList != null ? wfProcessMovementList : wfProcessList);
        model.addAttribute("managerWFProcessMovementsCount",
                wfProcessMovementList != null ? wfProcessMovementList.size() : wfProcessList.size());

        return "workflow/managerWFProcessMovements/managerWFProcessMovements";
    }

}
