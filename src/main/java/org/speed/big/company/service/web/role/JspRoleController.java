package org.speed.big.company.service.web.role;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.model.propertyeditor.RoleTypePropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

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
        modelAndView.addObject("allRoleTypes", super.getAllRoleType());

        return modelAndView;
    }

    @GetMapping("/filter")
    public String filter(){
        return "roles/roleFilter";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam String name,
                                     @RequestParam String description, @RequestParam int roleTypeId){

        //createRequestParam?name=testName....
        RoleType roleType = super.getRoleType(roleTypeId);

        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        role.setRoleTypeId(roleType);

        super.create(role);

        return "redirect:/roles";
    }

    @PostMapping("/createOrUpdateHSR")
    public String createOrUpdateHSR(HttpServletRequest request){
        RoleType roleType = super.getRoleType(
                Integer.parseInt(request.getParameter("roleTypeId")));

        Role role = new Role();
        role.setName(request.getParameter("name"));
        role.setDescription(request.getParameter("description"));
        role.setRoleTypeId(roleType);

        return "redirect:/roles";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute Role role){
        if (role.isNew())
            super.create(role);
        else
            super.update(role);

        return "redirect:/roles";
    }

    @GetMapping("/update/{id}")
    public String updateR(@PathVariable int id, Model model){
        model.addAttribute("role",super.get(id));
        model.addAttribute("allRoleTypes", super.getAllRoleType());

        return "roles/role";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getR(@PathVariable int id){
        return new ModelAndView("roles/role","role",super.get(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteR(@RequestParam int id){
        //delete/?id=0
        Role role = super.get(id);
        super.delete(role.getId());

        return "redirect:/roles";
    }

    @GetMapping("/getData/{id}")
    public String getData(@PathVariable int id, Model model){
        Role role = super.get(id);
        model.addAttribute("roleData", role);
        model.addAttribute("rRoleType", super.getRoleType(role.getRoleTypeId().getId()));

        return "roles/roleData";
    }

    @PostMapping("/filterRole")
    public ModelAndView filterRole(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/roles/roles");

        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));
        var description = parseString(request.getParameter("description"));
        var roleTypeId = parseInteger(request.getParameter("roleTypeId"));

        RoleType roleType = null;
        if (roleTypeId != null)
            roleType = super.getRoleType(roleTypeId);

        Role role = new Role();
        if (id != null)
            role.setId(id);
        if (name != null)
            role.setName(name);
        if (description != null)
            role.setDescription(description);
        if (roleType != null)
            role.setRoleTypeId(roleType);

        modelAndView.addObject("roles",super.filterRole(role));

        return modelAndView;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(RoleType.class, new RoleTypePropertyEditor());
    }
}
