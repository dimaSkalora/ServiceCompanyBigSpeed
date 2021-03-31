package org.speed.big.company.service.web.workflow.wf_process_status;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("wfProcessStatuses")
public class JspWFProcessStatusController extends AbstractWFProcessStatusController{
    @RequestMapping
    public String wfProcessStatuses(Model model){
        model.addAttribute("wfProcessStatuses",super.getAll());

        return "workflow/wfProcessStatuses/wfProcessStatuses";
    }

    @RequestMapping(value = "/wfProcessStatus",method = RequestMethod.GET)
    public ModelAndView wfProcessStatus(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessStatuses/wfProcessStatus");
        modelAndView.addObject("wfProcessStatus", new WFProcessStatus());

        return modelAndView;
    }

    @GetMapping("/newFilter")
    public String newFilter(){
        return "workflow/wfProcessStatuses/wfProcessStatusFilter";
    }

    @PostMapping("/createRequestParam")
    public String createRequestParam(@RequestParam String name){
        //wfProcessStatuses?name=testName
        WFProcessStatus wfProcessStatus = new WFProcessStatus();
        wfProcessStatus.setName(name);

        super.create(wfProcessStatus);

        return "redirect:/wfProcessStatuses";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        var name = request.getParameter("name");

        WFProcessStatus wfProcessStatus = new WFProcessStatus();
        wfProcessStatus.setName(name);

        super.create(wfProcessStatus);

        return "redirect:/wfProcessStatuses";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute WFProcessStatus wfProcessStatus, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "workflow/wfProcessStatuses/wfProcessStatus";

        if (wfProcessStatus.isNew())
            super.create(wfProcessStatus);
        else
            super.update(wfProcessStatus);

        return "redirect:/wfProcessStatuses";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessStatuses/wfProcessStatus");
        modelAndView.addObject("wfProcessStatus",super.get(id));

        return modelAndView;
    }

    @GetMapping("/get/{id}")
    public ModelAndView getWFPS(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessStatuses/wfProcessStatus");
        modelAndView.addObject("wfProcessStatus",super.get(id));

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteWFPS(@RequestParam int id){
        //delete?id=00000
        super.delete(id);
        return "redirect:/wfProcessStatuses";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessStatuses/wfProcessStatuses");
        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));

        WFProcessStatus wfProcessStatus = new WFProcessStatus();
        if (id != null)
            wfProcessStatus.setId(id);
        if (name != null)
            wfProcessStatus.setName(name);

        modelAndView.addObject("wfProcessStatuses", super.filter(wfProcessStatus));

        return modelAndView;
    }

}
