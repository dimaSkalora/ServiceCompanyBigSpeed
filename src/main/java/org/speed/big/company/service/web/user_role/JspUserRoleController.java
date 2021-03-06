package org.speed.big.company.service.web.user_role;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.model.propertyeditor.RolePropertyEditor;
import org.speed.big.company.service.model.propertyeditor.UserPropertyEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.speed.big.company.service.util.DateTimeUtil.parseLocalDate;
import static org.speed.big.company.service.util.DateTimeUtil.parseLocalDateTime;
import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("userRoles")
public class JspUserRoleController extends AbstractUserRoleController{
    @RequestMapping
    public String userRoles(Model model){
        model.addAttribute("userRoles", super.getAll());
        return "userRoles/userRoles";
    }

    @RequestMapping(value = "/userRole",method = RequestMethod.GET)
    public ModelAndView userRole(){
        ModelAndView modelAndView = new ModelAndView("userRoles/userRole");
        UserRole userRole = new UserRole();
        userRole.setDateTime(LocalDateTime.now());
        modelAndView.addObject("userRole", userRole);
        modelAndView.addObject("getAllUser", super.getAllUser());
        modelAndView.addObject("getAllRole",super.getAllRole());

        return modelAndView;
    }

    @GetMapping("/filter")
    public String filter(){
        return "userRoles/userRoleFilter";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam int userId,
                                     @RequestParam int roleId,
                                     @RequestParam LocalDateTime dateTime,
                                     @RequestParam String comment){
        //createRequestParam?userId=000&roleId=555&.....

        User user = super.getUser(userId);
        Role role = super.getRole(roleId);

        UserRole userRole = new UserRole();
        userRole.setUserId(user);
        userRole.setRoleId(role);
        userRole.setDateTime(dateTime);
        userRole.setComment(comment);
        super.create(userRole);

        return "redirect:userRoles/userRoles";
    }

    @PostMapping("/createOrUpdateHSR")
    public String createOrUpdateHSR(HttpServletRequest request){

        var userId = parseInteger(request.getParameter("userId"));
        var roleId = parseInteger(request.getParameter("roleId"));
        var dateTime = parseLocalDateTime(request.getParameter("dateTime"));
        var comment = parseString(request.getParameter("comment"));

        User user = super.getUser(userId);
        Role role = super.getRole(roleId);

        UserRole userRole = new UserRole();
        userRole.setUserId(user);
        userRole.setRoleId(role);
        userRole.setDateTime(dateTime);
        userRole.setComment(comment);
        super.create(userRole);

        return "redirect:/userRoles";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute UserRole userRole, BindingResult bindingResult,
                                 Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("getAllUser", super.getAllUser());
            model.addAttribute("getAllRole",super.getAllRole());
            return "userRoles/userRole";
        }

        if (userRole.isNew())
            super.create(userRole);
        else
            super.update(userRole, userRole.getId());

        return "redirect:/userRoles";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("userRoles/userRole");
        modelAndView.addObject("userRole", super.get(id));
        modelAndView.addObject("getAllUser", super.getAllUser());
        modelAndView.addObject("getAllRole",super.getAllRole());

        return modelAndView;
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String getUR(@RequestParam int id, Model model){
        model.addAttribute("userRole", super.get(id));

        return "userRoles/userRole";
    }

    @GetMapping("/delete")
    public String deleteUR(@RequestParam int id){
        //delete?id=00
        super.delete(id);

        return "redirect:/userRoles";
    }

    @GetMapping("/getData/{id}")
    public ModelAndView getData(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("userRoles/userRoleData");
        UserRole userRole = super.get(id);
        modelAndView.addObject("userRoleData",userRole);
        modelAndView.addObject("urUser",super.getUser(userRole.getUserId().getId()));
        modelAndView.addObject("urRole",super.getRole(userRole.getRoleId().getId()));

        return modelAndView;
    }

    @PostMapping("/filterUserRole")
    public ModelAndView filterUserRole(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("userRoles/userRoles");
        var id = parseInteger(request.getParameter("id"));
        var userId = parseInteger(request.getParameter("userId"));
        var roleId = parseInteger(request.getParameter("roleId"));
        var dateTime = parseLocalDateTime(request.getParameter("dateTime"));
        var comment = parseString(request.getParameter("comment"));

        User user = null;
        if (userId != null)
            user = super.getUser(userId);
        Role role = null;
        if (roleId != null)
            role = super.getRole(roleId);

        UserRole userRole = new UserRole();
        if (id != null)
            userRole.setId(id);
        if (user != null)
            userRole.setUserId(user);
        if (role != null)
            userRole.setRoleId(role);
        if (dateTime != null)
            userRole.setDateTime(dateTime);
        if (comment != null)
            userRole.setComment(comment);

        modelAndView.addObject("userRoles",super.filter(userRole));

        return modelAndView;
    }

    //Обявил глобально (GlobalBindingInitializer)
    /*//Method - который преабразовывает обьэкты в строки и наобород (при передачи параметров)
    @InitBinder
    public void initBinder(WebDataBinder binder){
        PropertyEditor editor = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (!text.trim().isEmpty())
                    super.setValue(LocalDateTime.parse(text.trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
            @Override
            public String getAsText() {
                if (super.getValue() == null)
                    return null;
                LocalDateTime value = (LocalDateTime) super.getValue();
                return value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        };

        binder.registerCustomEditor(LocalDateTime.class, editor);
        binder.registerCustomEditor(User.class, new UserPropertyEditor());
        binder.registerCustomEditor(Role.class, new RolePropertyEditor());
    }*/

}
