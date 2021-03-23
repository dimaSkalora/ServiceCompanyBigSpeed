package org.speed.big.company.service.web.workflow.wf_manager_process_movement;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("managerWFProcessMovements")
public class JspManagerWFProcessMovementController extends AbstractManagerWFProcessMovementController {

    /**
     * Получить список ролей и сервисов которые есть у юзера
     *
     * @param model
     * @return
     */
    @GetMapping
    public String managerWFProcessMovements(Model model){
        List<Role> getRoleFromUserRoles = super.getRoleFromUserRoles();
        List<WFService> getWFServiceFromRoles = super.getWFServiceFromRoles();

        model.addAttribute("getRoleFromUserRoles",getRoleFromUserRoles);
        model.addAttribute("getWFServiceFromRoles",getWFServiceFromRoles);

        return "workflow/managerWFProcessMovements/managerWFProcessMovements";
    }

    /**
     * Получаем список движения по ролях, сервисах
     * и статуса процесса: (В работе, Завершен, Архив, Ожидание)
     *
     * @param model
     * @return
     */
    @PostMapping("/processMovements")
    public String processMovements(HttpServletRequest request, Model model){
        var roleId = parseInteger(request.getParameter("roleId"));
        var wfServiceId = parseInteger(request.getParameter("wfServiceId"));
        var indexInWork = parseInteger(request.getParameter("indexInWork"));
        var indexCompleted = parseInteger(request.getParameter("indexCompleted"));
        var indexWaiting = parseInteger(request.getParameter("indexWaiting"));
        var indexArchive = parseInteger(request.getParameter("indexArchive"));

        List<WFProcessMovement> wfProcessMovementList = null;
        List<WFProcess> wfProcessList = null;

        if (indexInWork != null)
            wfProcessMovementList = super.getListWFProcessMovement(roleId,wfServiceId, indexInWork);
        if (indexWaiting != null)
            wfProcessMovementList = super.getListWFProcessMovement(roleId,wfServiceId,indexWaiting);

        if (indexCompleted != null)
            wfProcessList = super.getListWFProcess(wfServiceId,indexCompleted);
        if (indexArchive != null)
            wfProcessList = super.getListWFProcess(wfServiceId,indexArchive);

        model.addAttribute("managerWFProcessMovements",
                wfProcessMovementList != null ? wfProcessMovementList : wfProcessList);
        model.addAttribute("managerWFProcessMovementsCount",
                wfProcessMovementList != null ? wfProcessMovementList.size() : wfProcessList.size());

        return "workflow/managerWFProcessMovements/managerWFProcessMovements";
    }

    /**
     * Получаем процесс пакета по текущему движению
     *
     * @param wfPackId              -   Пакет
     * @param wfProcessId           -   Процесс
     * @param wfBaseProcessId       -   Базовый процесс
     * @return
     */
    @GetMapping("/wfProcessPackage/{wfPackId}/{wfProcessId}/{wfBaseProcessId}")
    public ModelAndView getWFProcessPackages(@PathVariable int wfPackId, @PathVariable int wfProcessId,
                                             @PathVariable int wfBaseProcessId){
        ModelAndView modelAndView = new ModelAndView("workflow/managerWFProcessMovements/wfProcessPackage");
        WFPackage wfPackage = super.getWFPackageOfProcessMovement(wfPackId);
        WFProcess wfProcess = super.getWFProcessById(wfProcessId);
        List<WFProcess> wfProcessList = super.getAllWFProcessByWFPackageId(wfPackage.getId());
        List<WFProcessMovement> wfProcessMovementList =
                super.getListWFPMByProcessAndBaseProcess(wfProcessId, wfBaseProcessId);

        modelAndView.addObject("wfPackage",wfPackage);
        modelAndView.addObject("wfProcess",wfProcess);
        modelAndView.addObject("wfProcessList",wfProcessList);
        modelAndView.addObject("wfProcessMovementList",wfProcessMovementList);

        return modelAndView;
    }

    /*Передать задание*/
    @GetMapping("/transferTasks/{processStateFromId}/{baseProcessId}/{wfProcessMovementId}")
    public ModelAndView transferTasks(@PathVariable int processStateFromId,
                                      @PathVariable int baseProcessId,@PathVariable int wfProcessMovementId){
        ModelAndView modelAndView = new ModelAndView("workflow/managerWFProcessMovements/transferTasks");

/*        var processStateFromId = parseInteger(request.getParameter("processStateFromId"));
        var baseProcessId = parseInteger(request.getParameter("baseProcessId"));
        var wfProcessMovementId = parseInteger(request.getParameter("wfProcessMovementId"));*/

        //Список состояний по которым можно передать задания
        List<WFProcessState> processStateList = super.getListTransferWFProcessState(processStateFromId,baseProcessId);

        modelAndView.addObject("processStateList", processStateList);
        modelAndView.addObject("wfProcessMovementId", wfProcessMovementId);

        return modelAndView;
    }

    /*Передать задания на выбраное состояние*/
    @PostMapping("/transferOnState")
    public String transferOnState(HttpServletRequest request){
        var wfProcessMovementId = parseInteger(request.getParameter("wfProcessMovementId"));
        var wfProcessMovementId2 = parseInteger(request.getParameter("wfProcessMovementId2"));
        var processStateToId = parseInteger(request.getParameter("processStateToId"));
        var description = parseString(request.getParameter("description"));

        System.out.println(wfProcessMovementId+"  "+wfProcessMovementId2+"  "+
                processStateToId+"  "+description);

        super.wfProcessMovementTransferTasks(wfProcessMovementId,processStateToId,description);

        return "redirect:/managerWFProcessMovements";
    }

}
