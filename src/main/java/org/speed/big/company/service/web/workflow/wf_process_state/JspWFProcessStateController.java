package org.speed.big.company.service.web.workflow.wf_process_state;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("wfProcessStates")
public class JspWFProcessStateController extends AbstractWFProcessStateController{
    @RequestMapping
    public String wfProcessStates(Model model){
        model.addAttribute("wfProcessStates",super.getAll());

        return "workflow/wfProcessStates/wfProcessStates";
    }

    @GetMapping("/wfProcessState")
    public ModelAndView wfProcessState(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessStates/wfProcessState");
        modelAndView.addObject("wfProcessState",new WFProcessState());
        modelAndView.addObject("getAllRoles",super.getAllRoles());
        modelAndView.addObject("getAllWFGroups",super.getAllWFGroups());

        return modelAndView;
    }

    @GetMapping("/newFilter")
    public String newFilter(){
        return "workflow/wfProcessStates/wfProcessStateFilter";
    }

    @RequestMapping(name = "/createRequestParam",method = RequestMethod.POST)
    public String createRequestParam(@RequestParam String name,
                                     @RequestParam Integer roleId,
                                     @RequestParam Integer groupId,
                                     @RequestParam(required = false) String description){
        //createRequestParam?name=nameTest?roleId=0000?...

        Role role = super.getRole(roleId);
        WFGroup wfGroup = super.getWFGroup(groupId);

        WFProcessState wfProcessState = new WFProcessState();
        wfProcessState.setName(name);
        wfProcessState.setRoleId(role);
        wfProcessState.setWfGroupId(wfGroup);
        if (description != null)
            wfProcessState.setDescription(description);

        super.create(wfProcessState);

        return "redirect:/wfProcessStates";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        var name = parseString(request.getParameter("name"));
        var roleId = parseInteger(request.getParameter("roleId"));
        var groupId = parseInteger(request.getParameter("groupId"));
        var description = parseString(request.getParameter("description"));

        Role role = super.getRole(roleId);
        WFGroup wfGroup = super.getWFGroup(groupId);

        WFProcessState wfProcessState = new WFProcessState();
        wfProcessState.setName(name);
        wfProcessState.setRoleId(role);
        wfProcessState.setWfGroupId(wfGroup);
        if (description != null)
            wfProcessState.setDescription(description);
        super.create(wfProcessState);

        return "redirect:/wfProcessStates";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute WFProcessState wfProcessState){
        if (wfProcessState.isNew())
            super.create(wfProcessState);
        else
            super.update(wfProcessState);

        return "redirect:/wfProcessStates";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getWFPS(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessStates/wfProcessState");
        modelAndView.addObject("wfProcessState",super.get(id));

        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public String update(int id, Model model){
        model.addAttribute("wfProcessState",super.get(id));
        model.addAttribute("getAllRoles",super.getAllRoles());
        model.addAttribute("getAllWFGroups",super.getAllWFGroups());

        return "workflow/wfProcessStates/wfProcessState";
    }

    @GetMapping("/delete")
    public String deleteWFPS(@RequestParam int id){
        //delete?id=000
        super.delete(id);

        return "redirect:/wfProcessStates";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfProcessStates/wfProcessStates");
        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));
        var roleId = parseInteger(request.getParameter("roleId"));
        var groupId = parseInteger(request.getParameter("groupId"));
        var description = parseString(request.getParameter("description"));

        Role role = null;
        WFGroup wfGroup = null;
        if (roleId != null)
            role = super.getRole(roleId);
        if (groupId != null)
            wfGroup = super.getWFGroup(groupId);

        WFProcessState wfProcessState = new WFProcessState();
        if (id != null)
            wfProcessState.setId(id);
        if (name != null)
            wfProcessState.setName(name);
        if (role != null)
            wfProcessState.setRoleId(role);
        if (wfGroup != null)
            wfProcessState.setWfGroupId(wfGroup);
        if (description != null)
            wfProcessState.setDescription(description);

        modelAndView.addObject("wfProcessStates",super.filter(wfProcessState));

        return modelAndView;
    }

    @GetMapping("/getData/{id")
    public String getData(@PathVariable int id, Model model){
        WFProcessState wfProcessState = super.get(id);
        Role role = super.getRole(wfProcessState.getRoleId().getId());
        WFGroup wfGroup = super.getWFGroup(wfProcessState.getWfGroupId().getId());

        model.addAttribute("wfProcessStateData",wfProcessState);
        model.addAttribute("wfpsRole",role);
        model.addAttribute("wfpsWFGroup",wfGroup);

        return "workflow/wfProcessStates/wfProcessStateData";
    }

    //Обявил глобально (GlobalBindingInitializer)
    //Method - который преабразовывает обьэкты в строки и наобород (при передачи параметров)
  /*  @InitBinder
    public void initBinder(WebDataBinder binder){
            binder.registerCustomEditor(Role.class, new RolePropertyEditor());
            binder.registerCustomEditor(WFGroup.class, new WFGroupPropertyEditor());
    }*/


}
