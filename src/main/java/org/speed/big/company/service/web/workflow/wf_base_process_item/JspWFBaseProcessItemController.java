package org.speed.big.company.service.web.workflow.wf_base_process_item;

import org.speed.big.company.service.model.propertyeditor.workflow.WFBaseProcessPropertyEditor;
import org.speed.big.company.service.model.propertyeditor.workflow.WFProcessStatePropertyEditor;
import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.speed.big.company.service.util.ParseUtil.parseInteger;

@Controller
@RequestMapping("wfBaseProcessItems")
public class JspWFBaseProcessItemController extends AbstractWFBaseProcessItemController{
    @RequestMapping
    public String wfBaseProcessItems(Model model){
        model.addAttribute("wfBaseProcessItems",super.getAll());
        return "workflow/wfBaseProcessItems/wfBaseProcessItems";
    }

    @RequestMapping(value = "/wfBaseProcessItem", method = RequestMethod.GET)
    public ModelAndView wfBaseProcessItem(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcessItems/wfBaseProcessItem");
        modelAndView.addObject("wfBaseProcessItem", new WFBaseProcessItem());
        modelAndView.addObject("getAllWFProcessStates", super.getAllWFProcessStates());
        modelAndView.addObject("getAllWFBaseProcesses", super.getAllWFBaseProcesses());

        return modelAndView;
    }

    @GetMapping("/newFilter")
    public String newFilter(){
        return "workflow/wfBaseProcessItems/wfBaseProcessItemFilter";
    }

    @RequestMapping("/createRequestParam")
    public String createRequestParam(@RequestParam(required = false) Integer stateFromId,
                                     @RequestParam int stateToId,
                                     @RequestParam int baseProcessId){
        //createRequestParam?stateFromId=000&stateToId=111&....

        WFProcessState wfProcessStateFrom = null;
        if (stateFromId != null)
            wfProcessStateFrom = super.getWFProcessState(stateFromId);
        WFProcessState wfProcessStateTo = super.getWFProcessState(stateToId);
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(baseProcessId);

        WFBaseProcessItem wfBaseProcessItem = new WFBaseProcessItem();
        if (wfProcessStateFrom != null)
            wfBaseProcessItem.setStateFromId(wfProcessStateFrom);
        wfBaseProcessItem.setStateToId(wfProcessStateTo);
        wfBaseProcessItem.setBaseProcessId(wfBaseProcess);

        super.create(wfBaseProcessItem);

        return "redirect:/wfBaseProcessItems";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        var stateFromId = parseInteger(request.getParameter("stateFromId"));
        var stateToId = parseInteger(request.getParameter("stateToId"));
        var baseProcessId = parseInteger(request.getParameter("baseProcessId"));

        WFProcessState wfProcessStateFrom = null;
        if (stateFromId != null)
            wfProcessStateFrom = super.getWFProcessState(stateFromId);
        WFProcessState wfProcessStateTo = super.getWFProcessState(stateToId);
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(baseProcessId);

        WFBaseProcessItem wfBaseProcessItem = new WFBaseProcessItem();
        if (wfProcessStateFrom != null)
            wfBaseProcessItem.setStateFromId(wfProcessStateFrom);
        wfBaseProcessItem.setStateToId(wfProcessStateTo);
        wfBaseProcessItem.setBaseProcessId(wfBaseProcess);

        super.create(wfBaseProcessItem);

        return "redirect:/wfBaseProcessItems";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute WFBaseProcessItem wfBaseProcessItem){
        if (wfBaseProcessItem.isNew())
            super.create(wfBaseProcessItem);
        else
            super.update(wfBaseProcessItem);

        return "redirect:/wfBaseProcessItems";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getWFBPI(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcessItems/wfBaseProcessItem");
        modelAndView.addObject("wfBaseProcessItem", super.get(id));

        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("wfBaseProcessItem", super.get(id));
        model.addAttribute("getAllWFProcessStates", super.getAllWFProcessStates());
        model.addAttribute("getAllWFBaseProcesses", super.getAllWFBaseProcesses());

        return "workflow/wfBaseProcessItems/wfBaseProcessItem";
    }

    @GetMapping("/delete")
    public String deleteWFBPI(@RequestParam int id){
        //delete?id=000
        super.delete(id);

        return "redirect:/wfBaseProcessItems";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcessItems/wfBaseProcessItems");
        var id = parseInteger(request.getParameter("id"));
        var stateFromId = parseInteger(request.getParameter("stateFromId"));
        var stateToId = parseInteger(request.getParameter("stateToId"));
        var baseProcessId = parseInteger(request.getParameter("baseProcessId"));

        WFProcessState wfProcessStateFrom = null;
        WFProcessState wfProcessStateTo = null;
        WFBaseProcess wfBaseProcess = null;

        if (stateFromId != null)
            wfProcessStateFrom = super.getWFProcessState(stateFromId);
        if (stateToId != null)
            wfProcessStateTo = super.getWFProcessState(stateToId);
        if (baseProcessId != null)
            wfBaseProcess = super.getWFBaseProcess(baseProcessId);

        WFBaseProcessItem wfBaseProcessItem = new WFBaseProcessItem();
        if (id != null)
            wfBaseProcessItem.setId(id);
        if (wfProcessStateFrom != null)
            wfBaseProcessItem.setStateFromId(wfProcessStateFrom);
        if (wfProcessStateTo != null)
            wfBaseProcessItem.setStateToId(wfProcessStateTo);
        if (wfBaseProcess != null)
            wfBaseProcessItem.setBaseProcessId(wfBaseProcess);

        List<WFBaseProcessItem> list = super.filter(wfBaseProcessItem);

        modelAndView.addObject("wfBaseProcessItems",list);

        return modelAndView;
    }

    @GetMapping("/getData/{id}")
    public ModelAndView getData(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcessItems/wfBaseProcessItemData");
        WFBaseProcessItem wfBaseProcessItem = super.get(id);
        WFProcessState wfProcessStateFrom = super.getWFProcessState(wfBaseProcessItem.getStateFromId().getId());
        WFProcessState wfProcessStateTo = super.getWFProcessState(wfBaseProcessItem.getStateToId().getId());
        WFBaseProcess wfBaseProcess = super.getWFBaseProcess(wfBaseProcessItem.getBaseProcessId().getId());
        modelAndView.addObject("wfBaseProcessItemData", wfBaseProcessItem);
        modelAndView.addObject("wfProcessStateFrom", wfProcessStateFrom);
        modelAndView.addObject("wfProcessStateTo", wfProcessStateTo);
        modelAndView.addObject("wfBaseProcess", wfBaseProcess);

        return modelAndView;
    }

    //Обявил глобально (GlobalBindingInitializer)
    //Method - который преабразовывает обьэкты в строки и наобород (при передачи параметров)
/*    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(WFProcessState.class,new WFProcessStatePropertyEditor());
        webDataBinder.registerCustomEditor(WFBaseProcess.class,new WFBaseProcessPropertyEditor());
    }*/


}
