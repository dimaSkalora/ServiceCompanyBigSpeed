package org.speed.big.company.service.web.workflow.wf_group;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("wfGroups")
public class JspWFGroupController extends AbstractWFGroupController {
    @RequestMapping
    public String wfGroups(Model model){
        model.addAttribute("wfGroups",super.getAll());
        return "workflow/wfGroups/wfGroups";
    }

    @GetMapping("/wfGroup")
    public ModelAndView wfGroup(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfGroups/wfGroup");
        modelAndView.addObject("wfGroup", new WFGroup());

        return modelAndView;
    }

    @RequestMapping(value = "/newFilter", method = RequestMethod.GET)
    public String newFilter(){
        return "workflow/wfGroups/wfGroupFilter";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam String name,
                                     @RequestParam(required = false) String description){
        //createRequestParam?name=nameTest?description...
        WFGroup wfGroup = new WFGroup();
        wfGroup.setName(name);
        if (description != null)
            wfGroup.setDescription(description);
        super.create(wfGroup);

        return "redirect:/wfGroups";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        var name = parseString(request.getParameter("name"));
        var description = parseString(request.getParameter("description"));

        WFGroup wfGroup = new WFGroup();
        if (name != null)
            wfGroup.setName(name);
        if (description != null)
            wfGroup.setDescription(description);

        super.create(wfGroup);

        return "redirect:/wfGroups";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute WFGroup wfGroup){
        if (wfGroup.isNew())
            super.create(wfGroup);
        else
            super.update(wfGroup);

        return "redirect:/wfGroups";
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable int id, Model model){
        WFGroup wfGroup = super.get(id);
        model.addAttribute("wfGroup",wfGroup);

        return "workflow/wfGroups/wfGroup";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfGroups/wfGroup");
        modelAndView.addObject("wfGroup", super.get(id));

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteWFG(@RequestParam int id){
        super.delete(id);
        return "redirect:/wfGroups";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfGroups/wfGroups");

        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));
        var description = parseString(request.getParameter("description"));

        WFGroup wfGroup = new WFGroup();
        if (id != null)
            wfGroup.setId(id);
        if (name != null)
            wfGroup.setName(name);
        if (description != null)
            wfGroup.setDescription(description);

        modelAndView.addObject("wfGroups",super.filter(wfGroup));

        return modelAndView;
    }
}
