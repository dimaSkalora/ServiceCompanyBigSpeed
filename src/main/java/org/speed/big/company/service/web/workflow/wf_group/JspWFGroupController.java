package org.speed.big.company.service.web.workflow.wf_group;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wfGroups")
public class JspWFGroupController extends AbstractWFGroupController {
    @RequestMapping
    public String wfGroups(Model model){
        model.addAttribute("wfGroups",super.getAll());
        return "workflow/wfGroups/wfGroups";
    }


}
