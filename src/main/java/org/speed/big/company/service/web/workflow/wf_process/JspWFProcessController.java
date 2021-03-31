package org.speed.big.company.service.web.workflow.wf_process;

import org.speed.big.company.service.model.propertyeditor.workflow.WFBaseProcessPropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.WFPackagePropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.WFProcessStatusPropertyEditor;
import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static org.speed.big.company.service.util.DateTimeUtil.parseLocalDateTime;
import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("wfProcesses")
public class JspWFProcessController extends AbstractWFProcessController{
    @RequestMapping
    public String wfProcesses(Model model){
        model.addAttribute("wfProcesses", super.getAll());
        return "workflow/wfProcesses/wfProcesses";
    }

    @GetMapping("/wfProcess")
    public ModelAndView wfProcess(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcesses/wfProcess");
        modelAndView.addObject("wfProcess", new WFProcess());
        modelAndView.addObject("allWFPackages", super.getAllWFPackages());
        modelAndView.addObject("allWFBaseProcesses", super.getAllWFBaseProcesses());
        modelAndView.addObject("allWFProcessStatuses", super.getAllWFProcessStatuses());

        return modelAndView;
    }

    @GetMapping("/newFilter")
    public String newFilter(){
        return "workflow/wfProcesses/wfProcessFilter";
    }

    @RequestMapping(value = "/createRequestParam",method = RequestMethod.POST)
    public String createRequestParam(@RequestParam LocalDateTime startDate,
                                     @RequestParam(required = false)LocalDateTime finalDate,
                                     @RequestParam(required = false)String description,
                                     @RequestParam LocalDateTime dateEdit,
                                     @RequestParam String userEdit,
                                     @RequestParam int wfPackageId,
                                     @RequestParam int wfBaseProcessId,
                                     @RequestParam int wfProcessStatusId){
        //createRequestParam?startDate=01.01.20&finalDate=...

        WFPackage wfPackage = super.getWFPackage(wfPackageId);
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(wfBaseProcessId);
        WFProcessStatus wfProcessStatus = super.getWFProcessStatus(wfProcessStatusId);

        WFProcess wfProcess = new WFProcess();
        wfProcess.setStartDate(startDate);
        if (finalDate != null)
            wfProcess.setFinalDate(finalDate);
        if (description != null)
            wfProcess.setDescription(description);
        wfProcess.setDateEdit(dateEdit);
        wfProcess.setUserEdit(userEdit);
        wfProcess.setWfPackageId(wfPackage);
        wfProcess.setWfBaseProcessId(wfBaseProcess);
        wfProcess.setWfProcessStatusId(wfProcessStatus);

        super.create(wfProcess);

        return "redirect:/wfProcesses";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        var startDate = parseLocalDateTime(request.getParameter("startDate"));
        var finalDate = parseLocalDateTime(request.getParameter("finalDate"));
        var description = parseString(request.getParameter("description"));
        var dateEdit = parseLocalDateTime(request.getParameter("dateEdit"));
        var userEdit = parseString(request.getParameter("userEdit"));
        var wfPackageId = parseInteger(request.getParameter("wfPackageId"));
        var wfBaseProcessId = parseInteger(request.getParameter("wfBaseProcessId"));
        var wfProcessStatusId = parseInteger(request.getParameter("wfProcessStatusId"));

        WFPackage wfPackage = super.getWFPackage(wfPackageId);
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(wfBaseProcessId);
        WFProcessStatus wfProcessStatus = super.getWFProcessStatus(wfProcessStatusId);

        WFProcess wfProcess = new WFProcess();
        wfProcess.setStartDate(startDate);
        if (finalDate != null)
            wfProcess.setFinalDate(finalDate);
        if (description != null)
            wfProcess.setDescription(description);
        wfProcess.setDateEdit(dateEdit);
        wfProcess.setUserEdit(userEdit);
        wfProcess.setWfPackageId(wfPackage);
        wfProcess.setWfBaseProcessId(wfBaseProcess);
        wfProcess.setWfProcessStatusId(wfProcessStatus);

        super.create(wfProcess);

        return "redirect:/wfProcesses";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute WFProcess wfProcess, BindingResult bindingResult,
                                 Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("allWFPackages", super.getAllWFPackages());
            model.addAttribute("allWFBaseProcesses", super.getAllWFBaseProcesses());
            model.addAttribute("allWFProcessStatuses", super.getAllWFProcessStatuses());

            return "workflow/wfProcesses/wfProcess";
        }

        if (wfProcess.isNew())
            super.create(wfProcess);
        else
            super.update(wfProcess);

        return "redirect:/wfProcesses";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcesses/wfProcess");
        modelAndView.addObject("wfProcess",super.get(id));
        modelAndView.addObject("allWFPackages", super.getAllWFPackages());
        modelAndView.addObject("allWFBaseProcesses", super.getAllWFBaseProcesses());
        modelAndView.addObject("allWFProcessStatuses", super.getAllWFProcessStatuses());

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteWFP(@RequestParam int id){
        //delete?id=115
        super.delete(id);

        return "redirect:/wfProcesses";
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable int id, Model model){
        WFProcess wfProcess = super.get(id);
        model.addAttribute("wfProcess", wfProcess);

        return "workflow/wfProcesses/wfProcess";
    }

    @GetMapping("/getData/{id}")
    public String getData(@PathVariable int id, Model model){
        WFProcess wfProcess = super.get(id);
        WFPackage wfPackage = super.getWFPackage(wfProcess.getWfPackageId().getId());
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(wfProcess.getWfBaseProcessId().getId());
        WFProcessStatus wfProcessStatus = super.getWFProcessStatus(wfProcess.getWfProcessStatusId().getId());

        model.addAttribute("wfProcessData",wfProcess);
        model.addAttribute("wfpWFPackage",wfPackage);
        model.addAttribute("wfpWFBaseProcess",wfBaseProcess);
        model.addAttribute("wfpWFProcessStatus",wfProcessStatus);

        return "workflow/wfProcesses/wfProcessData";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcesses/wfProcesses");
        WFPackage wfPackage = null;
        WFBaseProcess wfBaseProcess = null;
        WFProcessStatus wfProcessStatus = null;
        List<WFProcess> list = null;
        StringBuilder sqlCondition = new StringBuilder();

        var id = parseInteger(request.getParameter("id"));
        var startDate = parseLocalDateTime(request.getParameter("startDate"));
        var finalDate = parseLocalDateTime(request.getParameter("finalDate"));
        var description = parseString(request.getParameter("description"));
        var dateEdit = parseLocalDateTime(request.getParameter("dateEdit"));
        var userEdit = parseString(request.getParameter("userEdit"));
        var wfPackageId = parseInteger(request.getParameter("wfPackageId"));
        var wfBaseProcessId = parseInteger(request.getParameter("wfBaseProcessId"));
        var wfProcessStatusId = parseInteger(request.getParameter("wfProcessStatusId"));
        //sqlCondition
        var startDateFrom = parseLocalDateTime(request.getParameter("startDateFrom"));
        var startDateTo = parseLocalDateTime(request.getParameter("startDateTo"));
        var finalDateFrom = parseLocalDateTime(request.getParameter("finalDateFrom"));
        var finalDateTo = parseLocalDateTime(request.getParameter("finalDateTo"));

        if (wfPackageId != null)
            wfPackage = super.getWFPackage(wfPackageId);
        if (wfBaseProcessId != null)
            wfBaseProcess = super.getWFBaseProcess(wfBaseProcessId);
        if (wfProcessStatusId != null)
            wfProcessStatus = super.getWFProcessStatus(wfProcessStatusId);

        WFProcess wfProcess = new WFProcess();
        if (id != null)
            wfProcess.setId(id);
        if (startDate != null)
            wfProcess.setStartDate(startDate);
        if (finalDate != null)
            wfProcess.setFinalDate(finalDate);
        if (description != null)
            wfProcess.setDescription(description);
        if (dateEdit != null)
            wfProcess.setDateEdit(dateEdit);
        if (userEdit != null)
            wfProcess.setUserEdit(userEdit);
        if (wfPackage != null)
            wfProcess.setWfPackageId(wfPackage);
        if (wfBaseProcess != null)
            wfProcess.setWfBaseProcessId(wfBaseProcess);
        if (wfProcessStatus != null)
            wfProcess.setWfProcessStatusId(wfProcessStatus);

        if ((startDateFrom!=null)&&(startDateTo!=null))
            sqlCondition.append(" wfp.start_date between "+startDateFrom+" and "+startDateTo+"\n");
        if ((finalDateFrom!=null)&&(finalDateTo!=null))
            sqlCondition.append(" wfp.final_date between "+finalDateFrom+" and "+finalDateTo+"\n");

        list = super.filter(wfProcess,String.valueOf(sqlCondition));
        modelAndView.addObject("wfProcesses", list);

        return modelAndView;
    }

    //Обявил глобально (GlobalBindingInitializer)
    //Method - который преабразовывает обьэкты в строки и наобород (при передачи параметров)
   /* @InitBinder
    public void initBinder(WebDataBinder binder){
            binder.registerCustomEditor(WFPackage.class, new WFPackagePropertyEditor());
            binder.registerCustomEditor(WFBaseProcess.class, new WFBaseProcessPropertyEditor());
            binder.registerCustomEditor(WFProcessStatus.class, new WFProcessStatusPropertyEditor());
    }*/



}
