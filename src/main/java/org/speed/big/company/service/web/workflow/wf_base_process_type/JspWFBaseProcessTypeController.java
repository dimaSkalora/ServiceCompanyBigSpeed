package org.speed.big.company.service.web.workflow.wf_base_process_type;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
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
@RequestMapping("workflow/wfBaseProcessTypes")
public class JspWFBaseProcessTypeController extends AbstractWFBaseProcessTypeController{
    @RequestMapping
    public String wfBaseProcessTypes(Model model){
        model.addAttribute("wfBaseProcessTypes", super.getAll());

        return "workflow/wfBaseProcessTypes/wfBaseProcessTypes";
    }

    @RequestMapping(value = "/wfBaseProcessType",method = RequestMethod.GET)
    public ModelAndView wfBaseProcessType(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcessTypes/wfBaseProcessType");
        modelAndView.addObject("wfBaseProcessType", new WFBaseProcessType());

        return modelAndView;
    }

    @GetMapping("/newFilter")
    public String newFilter(){
        return "workflow/wfBaseProcessTypes/wfBaseProcessTypeFilter";
    }

    @PostMapping("/createRequestParam")
    public String createRequestParam(@RequestParam String name){
        //createRequestParam?name=testName
        WFBaseProcessType wfBaseProcessType = new WFBaseProcessType();
        wfBaseProcessType.setName(name);

        super.create(wfBaseProcessType);

        return "redirect:/workflow/wfBaseProcessTypes";
    }

    @RequestMapping(value = "/createHSR",method = RequestMethod.POST)
    public String createHSR(HttpServletRequest request){
        WFBaseProcessType wfBaseProcessType = new WFBaseProcessType();
        wfBaseProcessType.setName(request.getParameter("name"));

        super.create(wfBaseProcessType);

        return "redirect:/workflow/wfBaseProcessTypes";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute WFBaseProcessType wfBaseProcessType, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "workflow/wfBaseProcessTypes/wfBaseProcessType";

        if (wfBaseProcessType.isNew())
            super.create(wfBaseProcessType);
        else
            super.update(wfBaseProcessType, wfBaseProcessType.getId());

        return "redirect:/workflow/wfBaseProcessTypes";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcessTypes/wfBaseProcessType");
        modelAndView.addObject("wfBaseProcessType", super.get(id));

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteWFBPT(@RequestParam int id){
        //delete?id=00000
        super.delete(id);

        return "redirect:/workflow/wfBaseProcessTypes";
    }


    @GetMapping("/get/{id}")
    public ModelAndView getWFBPT(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcessTypes/wfBaseProcessType");
        modelAndView.addObject("wfBaseProcessType", super.get(id));

        return modelAndView;
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfBaseProcessTypes/wfBaseProcessTypes");
        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));

        WFBaseProcessType wfBaseProcessType = new WFBaseProcessType();
        if (id != null)
            wfBaseProcessType.setId(id);
        if (name != null)
            wfBaseProcessType.setName(name);

        modelAndView.addObject("wfBaseProcessTypes",super.filter(wfBaseProcessType));

        return modelAndView;
    }

}
