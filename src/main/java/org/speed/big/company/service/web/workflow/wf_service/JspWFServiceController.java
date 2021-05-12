package org.speed.big.company.service.web.workflow.wf_service;

import org.speed.big.company.service.model.workflow.WFService;
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
@RequestMapping("workflow/wfServices")
public class JspWFServiceController extends AbstractWFServiceController{
    @RequestMapping(method = RequestMethod.GET)
    public String wfServices(Model model){
        model.addAttribute("wfServices",super.getAll());
        return "workflow/wfServices/wfServices";
    }

    @GetMapping("/wfService")
    public ModelAndView wfService(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfServices/wfService");
        modelAndView.addObject("wfService", new WFService());

        return modelAndView;
    }

    @GetMapping("/wfsFilter")
    public String wfsFilter(){
        return "workflow/wfServices/wfServiceFilter";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam String name){
        //createRequestParam?name=testName....

        WFService wfService = new WFService();
        wfService.setName(name);
        super.create(wfService);

        return "redirect:/workflow/wfServices";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        WFService wfService = new WFService();
        wfService.setName(request.getParameter("name"));
        super.create(wfService);

        return "redirect:/workflow/wfServices";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute WFService wfService, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "workflow/wfServices/wfService";

        if (wfService.isNew())
            super.create(wfService);
        else
            super.update(wfService);

        return "redirect:/workflow/wfServices";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfServices/wfService");
        modelAndView.addObject("wfService", super.get(id));

        return modelAndView;
    }

    @GetMapping("/get/{id}")
    public ModelAndView getWFS(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfServices/wfService");
        modelAndView.addObject("wfService", super.get(id));

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteWFS(@RequestParam int id){
        //delete?id=0000
        super.delete(id);

        return "redirect:/workflow/wfServices";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfServices/wfServices");
        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));

        WFService wfService = new WFService();
        if (id != null)
            wfService.setId(id);
        if (name != null)
            wfService.setName(name);

        modelAndView.addObject("wfServices",super.filter(wfService));

        return modelAndView;
    }
}
