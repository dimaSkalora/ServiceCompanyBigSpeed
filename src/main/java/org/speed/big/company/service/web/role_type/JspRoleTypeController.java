package org.speed.big.company.service.web.role_type;

import org.speed.big.company.service.model.RoleType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("roleTypes")
public class JspRoleTypeController extends AbstractRoleTypeController{
    @RequestMapping
    public String roleTypes(Model model){
        model.addAttribute("roleTypes",super.getAll());

        return "roleTypes/roleTypes";
    }

    @GetMapping("/roleType")
    public ModelAndView roleType(){
        return new ModelAndView("roleTypes/roleType","roleType", new RoleType());
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filter(Model model){
        model.addAttribute("filterRT", new RoleType());
        return "roleTypes/roleTypeFilter";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam String name){
        //createRequestParam?name=testName

        RoleType roleType = new RoleType();
        roleType.setName(name);
        super.create(roleType);

        return "redirect:/roleTypes";
    }

    @PostMapping("/createOrUpdateHSR")
    public ModelAndView createOrUpdateHSR(HttpServletRequest request){
        RedirectView redirectView = new RedirectView("roleTypes");
        ModelAndView modelAndView = new ModelAndView(redirectView);
        RoleType roleType = new RoleType();
        roleType.setName(request.getParameter("name"));
        super.create(roleType);

        return modelAndView;
    }

    @RequestMapping(value = "/createOrUpdate",method = RequestMethod.POST)
    public String createOrUpdate(@ModelAttribute RoleType roleType){
        if (roleType.isNew())
            super.create(roleType);
        else
            super.update(roleType);

        return "redirect:/roleTypes";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model){
        RoleType roleType = super.get(id);
        model.addAttribute("roleType",roleType);

        return "roleTypes/roleType";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getRT(@PathVariable int id){
        //get/0
        RoleType roleType = super.get(id);

        return new ModelAndView("roleTypes/roleType","roleType",roleType);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteRT(@RequestParam int id){
        //delete?id=0
        RoleType roleType = get(id);
        super.delete(roleType.getId());

        return "redirect:roleTypes";
    }

    @PostMapping("/filterRoleType")
    public ModelAndView filterRoleType(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("roleTypes/roleTypes");

        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));

        RoleType roleType = new RoleType();
        if (id != null)
            roleType.setId(id);
        if (name != null)
            roleType.setName(name);

        modelAndView.addObject("roleTypes",super.filterRoleType(roleType));

        return modelAndView;
    }

}
