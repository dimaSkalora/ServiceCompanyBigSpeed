package org.speed.big.company.service.web.workflow.wf_package_status;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
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
@RequestMapping("workflow/wfPackageStatuses")
public class JspWFPackageStatusController extends AbstractWFPackageStatusController{

    @RequestMapping(method = RequestMethod.GET)
    public String wfPackageStatuses(Model model){
        model.addAttribute("wfPackageStatuses",super.getAll());
        return "workflow/wfPackageStatuses/wfPackageStatuses";
    }

    @GetMapping("/wfPackageStatus")
    public ModelAndView wfPackageStatus(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfPackageStatuses/wfPackageStatus");
        modelAndView.addObject("wfPackageStatus", new WFPackageStatus());

        return modelAndView;
    }

    @RequestMapping(value = "/wfPackageStatusFilter",method = RequestMethod.GET)
    public String newFilter(){
        return "workflow/wfPackageStatuses/wfPackageStatusFilter";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam String name){
        //createRequestParam?name=testName....

        WFPackageStatus wfPackageStatus = new WFPackageStatus();
        wfPackageStatus.setName(name);

        super.create(wfPackageStatus);

        return "redirect:/workflow/wfPackageStatuses";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest httpServletRequest){
        WFPackageStatus wfPackageStatus = new WFPackageStatus();
        wfPackageStatus.setName(httpServletRequest.getParameter("name"));
        super.create(wfPackageStatus);

        return "redirect:/workflow/wfPackageStatuses";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute WFPackageStatus wfPackageStatus, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "workflow/wfPackageStatuses/wfPackageStatus";

        if (wfPackageStatus.isNew())
            super.create(wfPackageStatus);
        else
            super.update(wfPackageStatus, wfPackageStatus.getId());

        return "redirect:/workflow/wfPackageStatuses";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("wfPackageStatus",super.get(id));

        return "workflow/wfPackageStatuses/wfPackageStatus";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteWFPS(@RequestParam int id){
        //delete?id=0000
        super.delete(id);

        return "redirect:/workflow/wfPackageStatuses";
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable int id, Model model){
        model.addAttribute("wfPackageStatus",super.get(id));

        return "workflow/wfPackageStatuses/wfPackageStatus";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfPackageStatuses/wfPackageStatuses");
        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));

        WFPackageStatus wfPackageStatus = new WFPackageStatus();
        if (id != null)
            wfPackageStatus.setId(id);
        if (name != null)
            wfPackageStatus.setName(name);

        modelAndView.addObject("wfPackageStatuses",super.filter(wfPackageStatus));

        return modelAndView;
    }

}
