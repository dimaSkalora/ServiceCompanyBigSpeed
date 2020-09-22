package org.speed.big.company.service.web.role;

import org.speed.big.company.service.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("roles")
public class JspRoleController extends AbstractRoleController{
    @RequestMapping
    public String roles(Model model){
        model.addAttribute("roles", super.getAll());
        return "roles/roles";
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ModelAndView role(){
        ModelAndView modelAndView = new ModelAndView("roles/role");
        modelAndView.addObject("role", new Role());
        modelAndView.addObject("allRoleType", super.getAllRoleType());

        return modelAndView;
    }

    @GetMapping("/filter")
    public String filter(){
        return "roles/roleFilter";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(){

        return "redirect:/roles";
    }


}
