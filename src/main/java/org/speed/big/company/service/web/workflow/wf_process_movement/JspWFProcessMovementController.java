package org.speed.big.company.service.web.workflow.wf_process_movement;

import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.propertyeditor.UserPropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.WFBaseProcessPropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.WFPackagePropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.WFProcessPropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.WFProcessStatusPropertyEditor;
import org.speed.big.company.service.model.workflow.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static org.speed.big.company.service.util.DateTimeUtil.parseLocalDateTime;
import static org.speed.big.company.service.util.ParseUtil.*;

@Controller
@RequestMapping("wfProcessMovements")
public class JspWFProcessMovementController extends AbstractWFProcessMovementController{
    @RequestMapping
    public String wfProcessMovements(Model model){
        model.addAttribute("wfProcessMovements",super.getAll());
        return "workflow/wfProcessMovements/wfProcessMovements";
    }

    @GetMapping("/wfProcessMovement")
    public ModelAndView wfProcessMovement(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessMovements/wfProcessMovement");
        modelAndView.addObject("wfProcessMovement", new WFProcessMovement());

        return modelAndView;
    }

    @GetMapping("/newFilter")
    public String newFilter(){
        return "workflow/wfProcessMovements/wfProcessMovementFilter";
    }

    @PostMapping("/createRequestParam")
    public String createRequestParam(@RequestParam LocalDateTime startDateTime,
                                     @RequestParam(required = false) LocalDateTime finalDateTime,
                                     @RequestParam boolean isCompleted,
                                     @RequestParam(required = false) String description,
                                     @RequestParam LocalDateTime dateEdit,
                                     @RequestParam String userEdit,
                                     @RequestParam int userId,
                                     @RequestParam int wfPackageId,
                                     @RequestParam int wfStateId,
                                     @RequestParam int wfProcessId,
                                     @RequestParam int wfBaseProcessId,
                                     @RequestParam boolean isLast){
        //createRequestParam?startDateTime=00.00.0000?isCompleted=true?.....

        User user = super.getUser(userId);
        WFPackage wfPackage = super.getWFPackage(wfPackageId);
        WFProcessState wfProcessState = super.getWFProcessState(wfStateId);
        WFProcess wfProcess = super.getWFProcess(wfProcessId);
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(wfBaseProcessId);

        WFProcessMovement wfProcessMovement = new WFProcessMovement();
        wfProcessMovement.setStartDateTime(startDateTime);
        if (finalDateTime != null)
            wfProcessMovement.setFinalDateTime(finalDateTime);
        wfProcessMovement.setCompleted(isCompleted);
        if (description != null)
            wfProcessMovement.setDescription(description);
        wfProcessMovement.setDateEdit(dateEdit);
        wfProcessMovement.setUserEdit(userEdit);
        wfProcessMovement.setUserId(user);
        wfProcessMovement.setWfPackageId(wfPackage);
        wfProcessMovement.setWfStateId(wfProcessState);
        wfProcessMovement.setWfProcessId(wfProcess);
        wfProcessMovement.setWfBaseProcessId(wfBaseProcess);
        wfProcessMovement.setLast(isLast);

        super.create(wfProcessMovement);

        return "redirect:/wfProcessMovements";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        var startDateTime = parseLocalDateTime(request.getParameter("startDateTime"));
        var finalDateTime = parseLocalDateTime(request.getParameter("finalDateTime"));
        var isCompleted = parseBoolean(request.getParameter("isCompleted"));
        var description = parseString(request.getParameter("description"));
        var dateEdit = parseLocalDateTime(request.getParameter("dateEdit"));
        var userEdit = request.getParameter("userEdit");
        var userId = parseInteger(request.getParameter("userId"));
        var wfPackageId = parseInteger(request.getParameter("wfPackageId"));
        var wfStateId = parseInteger(request.getParameter("wfStateId"));
        var wfProcessId = parseInteger(request.getParameter("wfProcessId"));
        var wfBaseProcessId = parseInteger(request.getParameter("wfBaseProcessId"));
        var isLast = parseBoolean(request.getParameter("isLast"));

        User user = super.getUser(userId);
        WFPackage wfPackage = super.getWFPackage(wfPackageId);
        WFProcessState wfProcessState = super.getWFProcessState(wfStateId);
        WFProcess wfProcess = super.getWFProcess(wfProcessId);
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(wfBaseProcessId);

        WFProcessMovement wfProcessMovement = new WFProcessMovement();
        wfProcessMovement.setStartDateTime(startDateTime);
        if (finalDateTime != null)
            wfProcessMovement.setFinalDateTime(finalDateTime);
        wfProcessMovement.setCompleted(isCompleted);
        if (description != null)
            wfProcessMovement.setDescription(description);
        wfProcessMovement.setDateEdit(dateEdit);
        wfProcessMovement.setUserEdit(userEdit);
        wfProcessMovement.setUserId(user);
        wfProcessMovement.setWfPackageId(wfPackage);
        wfProcessMovement.setWfStateId(wfProcessState);
        wfProcessMovement.setWfProcessId(wfProcess);
        wfProcessMovement.setWfBaseProcessId(wfBaseProcess);
        wfProcessMovement.setLast(isLast);

        super.create(wfProcessMovement);

        return "redirect:/wfProcessMovements";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute WFProcessMovement wfProcessMovement){
        if (wfProcessMovement.isNew())
            super.create(wfProcessMovement);
        else
            super.update(wfProcessMovement);

        return "redirect:/wfProcessMovements";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessMovements/wfProcessMovement");
        WFProcessMovement wfProcessMovement = super.get(id);
        modelAndView.addObject("wfProcessMovement",wfProcessMovement);

        return modelAndView;
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable int id, Model model){
        WFProcessMovement wfProcessMovement = super.get(id);
        model.addAttribute("wfProcessMovement",wfProcessMovement);

        return "workflow/wfProcessMovements/wfProcessMovement";
    }

    @GetMapping("/delete")
    public String deleteWFPM(@RequestParam int id){
        //delete?id=000
        super.delete(id);
        return "redirect:/wfProcessMovements";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessMovements/wfProcessMovementFilter");
        List<WFProcessMovement> list = null;
        StringBuilder sqlCondition = new StringBuilder();
        //WFProcessMovement
        var id = parseInteger(request.getParameter("id"));
        var startDateTime = parseLocalDateTime(request.getParameter("startDateTime"));
        var finalDateTime = parseLocalDateTime(request.getParameter("finalDateTime"));
        var isCompleted = parseBoolean(request.getParameter("isCompleted"));
        var description = parseString(request.getParameter("description"));
        var dateEdit = parseLocalDateTime(request.getParameter("dateEdit"));
        var userEdit = request.getParameter("userEdit");
        var userId = parseInteger(request.getParameter("userId"));
        var wfPackageId = parseInteger(request.getParameter("wfPackageId"));
        var wfStateId = parseInteger(request.getParameter("wfStateId"));
        var wfProcessId = parseInteger(request.getParameter("wfProcessId"));
        var wfBaseProcessId = parseInteger(request.getParameter("wfBaseProcessId"));
        var isLast = parseBoolean(request.getParameter("isLast"));
        //sqlCondition
        var startDateTimeFrom = parseLocalDateTime(request.getParameter("startDateTimeFrom"));
        var startDateTimeTo = parseLocalDateTime(request.getParameter("startDateTimeTo"));
        var finalDateTimeFrom = parseLocalDateTime(request.getParameter("finalDateTimeFrom"));
        var finalDateTimeTo = parseLocalDateTime(request.getParameter("finalDateTimeTo"));

        User user = null;
        WFPackage wfPackage = null;
        WFProcessState wfProcessState = null;
        WFProcess wfProcess = null;
        WFBaseProcess wfBaseProcess = null;
        if (userId != null)
            user = super.getUser(userId);
        if (wfPackageId != null)
            wfPackage = super.getWFPackage(wfPackageId);
        if (wfProcessState != null)
            wfProcessState = super.getWFProcessState(wfStateId);
        if (wfProcess != null)
            wfProcess = super.getWFProcess(wfProcessId);
        if (wfBaseProcess != null)
            wfBaseProcess = super.getWFBaseProcess(wfBaseProcessId);

        WFProcessMovement wfProcessMovement = new WFProcessMovement();
        if (id != null)
            wfProcessMovement.setId(id);
        if (startDateTime != null)
            wfProcessMovement.setStartDateTime(startDateTime);
        if (finalDateTime != null)
            wfProcessMovement.setFinalDateTime(finalDateTime);
        wfProcessMovement.setCompleted(isCompleted);
        if (description != null)
            wfProcessMovement.setDescription(description);
        if (dateEdit != null)
            wfProcessMovement.setDateEdit(dateEdit);
        if (userEdit != null)
            wfProcessMovement.setUserEdit(userEdit);
        if (user != null)
            wfProcessMovement.setUserId(user);
        if (wfPackage != null)
            wfProcessMovement.setWfPackageId(wfPackage);
        if (wfProcessState != null)
            wfProcessMovement.setWfStateId(wfProcessState);
        if (wfProcess != null)
            wfProcessMovement.setWfProcessId(wfProcess);
        if (wfBaseProcess != null)
            wfProcessMovement.setWfBaseProcessId(wfBaseProcess);
        wfProcessMovement.setLast(isLast);

        if ((startDateTimeFrom != null) && (startDateTimeTo != null))
            sqlCondition.append(" wfpm.start_date_time between "+startDateTimeFrom+" and "+startDateTimeTo+"\n");
        if ((finalDateTimeFrom != null) && (finalDateTimeTo != null))
            if ((startDateTimeFrom != null) && (startDateTimeTo != null))
                sqlCondition.append(" and wfpm.final_date_time between "+finalDateTimeFrom+" and "+finalDateTimeTo+"\n");
            else
                sqlCondition.append(" wfpm.final_date_time between "+finalDateTimeFrom+" and "+finalDateTimeTo+"\n");

        list = super.filter(wfProcessMovement, String.valueOf(sqlCondition));

        modelAndView.addObject("wfProcessMovements",list);

        return modelAndView;
    }

    @GetMapping("/getData/{id}")
    public ModelAndView getData(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessMovements/wfProcessMovementData");
        WFProcessMovement wfProcessMovement = super.get(id);
        User user = super.getUser(wfProcessMovement.getUserId().getId());
        WFPackage wfPackage = super.getWFPackage(wfProcessMovement.getWfPackageId().getId());
        WFProcessState wfProcessState = super.getWFProcessState(wfProcessMovement.getWfStateId().getId());
        WFProcess wfProcess = super.getWFProcess(wfProcessMovement.getWfProcessId().getId());
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(wfProcessMovement.getWfBaseProcessId().getId());

        modelAndView.addObject("wfProcessMovementData",wfProcessMovement);
        modelAndView.addObject("wfpmUser",user);
        modelAndView.addObject("wfpmWFPackage",wfPackage);
        modelAndView.addObject("wfpmWFProcessState",wfProcessState);
        modelAndView.addObject("wfpmWFProcess",wfProcess);
        modelAndView.addObject("wfpmWFBaseProcess",wfBaseProcess);

        return modelAndView;
    }

    //Обявил глобально (GlobalBindingInitializer)
    //Method - который преабразовывает обьэкты в строки и наобород (при передачи параметров)
/*    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(User.class, new UserPropertyEditor());
        binder.registerCustomEditor(WFPackage.class, new WFPackagePropertyEditor());
        binder.registerCustomEditor(WFProcessStatus.class, new WFProcessStatusPropertyEditor());
        binder.registerCustomEditor(WFProcess.class,new WFProcessPropertyEditor());
        binder.registerCustomEditor(WFBaseProcess.class, new WFBaseProcessPropertyEditor());
    }*/

}
