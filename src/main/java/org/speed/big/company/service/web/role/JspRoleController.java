package org.speed.big.company.service.web.role;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("roles")
public class JspRoleController extends AbstractRoleController{
    @RequestMapping
    public String roles(Model model){
        model.addAttribute("roles", super.getAll());
        return "roles/roles";
    }

}
