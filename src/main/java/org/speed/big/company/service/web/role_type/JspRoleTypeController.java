package org.speed.big.company.service.web.role_type;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("roleTypes")
public class JspRoleTypeController extends AbstractRoleTypeController{
    @RequestMapping
    public String roleTypes(Model model){
        model.addAttribute("roleTypes",super.getAll());

        return "roleTypes/roleTypes";
    }


}
