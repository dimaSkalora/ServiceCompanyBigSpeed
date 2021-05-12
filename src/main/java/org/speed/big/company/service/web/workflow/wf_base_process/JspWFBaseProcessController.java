package org.speed.big.company.service.web.workflow.wf_base_process;

import org.speed.big.company.service.model.propertyeditor.workflow.WFBaseProcessTypePropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.WFServicePropertyEditor;
import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("workflow/wfBaseProcesses")
public class JspWFBaseProcessController extends AbstractWFBaseProcessController{
    @RequestMapping
    public ModelAndView wfBaseProcesses(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcesses/wfBaseProcesses");
        modelAndView.addObject("wfBaseProcesses", super.getAll());

        return modelAndView;
    }

    @RequestMapping(value = "/wfBaseProcess",method = RequestMethod.GET)
    public ModelAndView wfBaseProcess(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcesses/wfBaseProcess");
        modelAndView.addObject("wfBaseProcess", new WFBaseProcess());
        modelAndView.addObject("allWFS", super.getAllWFS());
        modelAndView.addObject("allWFBPT", super.getAllWFBPT());

        return modelAndView;
    }

    @GetMapping("/newFilter")
    public String newFilter(){
        return "workflow/wfBaseProcesses/wfBaseProcessFilter";
    }

    @PostMapping("/createRequestParam")
    public String createRequestParam(@RequestParam String name,
                                     @RequestParam(required = false) String description,
                                     @RequestParam int wfServiceId,
                                     @RequestParam int wfBaseProcessTypeId){
        //createRequestParam?name=testName&description=testDescription&....

        WFService wfService = super.getWFS(wfServiceId);
        WFBaseProcessType wfBaseProcessType = super.getWFBPT(wfBaseProcessTypeId);

        WFBaseProcess wfBaseProcess = new WFBaseProcess();
        wfBaseProcess.setName(name);
        if (description != null)
            wfBaseProcess.setDescription(description);
        wfBaseProcess.setWfServiceId(wfService);
        wfBaseProcess.setWfBaseProcessTypeId(wfBaseProcessType);
        super.create(wfBaseProcess);

        return "redirect:/workflow/wfBaseProcesses";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        var name = request.getParameter("name");
        var description = parseString(request.getParameter("description"));
        var wfServiceId = parseInteger(request.getParameter("wfServiceId"));
        var wfBaseProcessTypeId = parseInteger(request.getParameter("wfBaseProcessTypeId"));

        WFService wfService = super.getWFS(wfServiceId);
        WFBaseProcessType wfBaseProcessType = super.getWFBPT(wfBaseProcessTypeId);

        WFBaseProcess wfBaseProcess = new WFBaseProcess();
        wfBaseProcess.setName(name);
        if (description != null)
            wfBaseProcess.setDescription(description);
        wfBaseProcess.setWfServiceId(wfService);
        wfBaseProcess.setWfBaseProcessTypeId(wfBaseProcessType);

        super.create(wfBaseProcess);

        return "redirect:/workflow/wfBaseProcesses";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute WFBaseProcess wfBaseProcess, BindingResult bindingResult,
                                 Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("allWFS", super.getAllWFS());
            model.addAttribute("allWFBPT", super.getAllWFBPT());

            return "workflow/wfBaseProcesses/wfBaseProcess";
        }

        if (wfBaseProcess.isNew())
            super.create(wfBaseProcess);
        else
            super.update(wfBaseProcess);

        return "redirect:/workflow/wfBaseProcesses";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("wfBaseProcess",super.get(id));
        model.addAttribute("allWFS", super.getAllWFS());
        model.addAttribute("allWFBPT", super.getAllWFBPT());

        return "workflow/wfBaseProcesses/wfBaseProcess";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getWFBP(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcesses/wfBaseProcess");
        modelAndView.addObject("wfBaseProcess", super.get(id));

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteWFBP(int id){
        //delete?id=000
        super.delete(id);

        return "redirect:/workflow/wfBaseProcesses";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcesses/wfBaseProcesses");
        List<WFBaseProcess> list;

        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));
        var description = parseString(request.getParameter("description"));
        var wfServiceId = parseInteger(request.getParameter("wfServiceId"));
        var wfBaseProcessTypeId = parseInteger(request.getParameter("wfBaseProcessTypeId"));

        WFService wfService = null;
        if (wfServiceId != null)
            wfService = super.getWFS(wfServiceId);
        WFBaseProcessType wfBaseProcessType = null;
        if (wfBaseProcessTypeId != null)
            wfBaseProcessType = super.getWFBPT(wfBaseProcessTypeId);

        WFBaseProcess wfBaseProcess = new WFBaseProcess();
        if (id != null)
            wfBaseProcess.setId(id);
        if (name != null)
            wfBaseProcess.setName(name);
        if (description != null)
            wfBaseProcess.setDescription(description);
        if (wfService != null)
            wfBaseProcess.setWfServiceId(wfService);
        if (wfBaseProcessType != null)
            wfBaseProcess.setWfBaseProcessTypeId(wfBaseProcessType);

        list = super.filter(wfBaseProcess);
        modelAndView.addObject("wfBaseProcesses",list);

        return modelAndView;
    }

    @GetMapping("/getData/{id}")
    public ModelAndView getData(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcesses/wfBaseProcessData");
        WFBaseProcess wfBaseProcess = super.get(id);
        WFService wfService = super.getWFS(wfBaseProcess.getWfServiceId().getId());
        WFBaseProcessType wfBaseProcessType = super.getWFBPT(wfBaseProcess.getWfBaseProcessTypeId().getId());
        modelAndView.addObject("wfbpData",wfBaseProcess);
        modelAndView.addObject("wfbpWFService",wfService);
        modelAndView.addObject("wfbpWFBaseProcessType",wfBaseProcessType);

        return modelAndView;
    }


    //Обявил глобально (GlobalBindingInitializer)
    //Method - который преабразовывает обьэкты в строки и наобород (при передачи параметров)
  /*  @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(WFService.class, new WFServicePropertyEditor());
        binder.registerCustomEditor(WFBaseProcessType.class, new WFBaseProcessTypePropertyEditor());
    }*/

}
