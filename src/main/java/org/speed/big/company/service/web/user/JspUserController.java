package org.speed.big.company.service.web.user;

import org.speed.big.company.service.model.User;
import org.speed.big.company.service.util.DateTimeUtil;
import org.speed.big.company.service.util.ParseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("users")
public class JspUserController extends AbstractUserController{
    @RequestMapping
    public String users(Model model){
        model.addAttribute("users",super.getAll());

        return "users/users";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView("users/user");
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @GetMapping("/filter")
    public String filter(Model model){
        model.addAttribute("filterUser", new User());

        return "users/userFilter";
    }

    @RequestMapping(value = "/createRequestParam",method = RequestMethod.POST)
    public String createRequestParam(@RequestParam(required = true) String name,
                                     @RequestParam String email, @RequestParam String password,
                                     @RequestParam String phone, @RequestParam LocalDate registered,
                                     @RequestParam Boolean enabled){
        //createRequestParam?name=test&email=test@test.....

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRegistered(registered);
        user.setEnabled(enabled);
        super.create(user);

        return "redirect:/users";
    }

    @PostMapping("/createOrUpdateHSR")
    public String createOrUpdateHSR(HttpServletRequest request){

        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setPhone(request.getParameter("phone"));
        user.setRegistered(LocalDate.parse(request.getParameter("registered")));
        user.setEnabled(Boolean.valueOf(request.getParameter("enabled")));
        super.create(user);

        return "redirect:/users";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "users/user";

        if (user.isNew()){
            user.setRegistered(LocalDate.now());
            user.setEnabled(true);
            super.create(user);
        }else
            super.update(user, user.getId());

        return "redirect:/users";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int id){
        User user = super.get(id);
        return new ModelAndView("users/user", "user", user);
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam int id){
        //delete?id=5555
        super.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/getUserFromAllRoles/{id}", method = RequestMethod.GET)
    public String getUserFromAllRoles(@PathVariable int id, Model model){
        User userFromAllRoles = super.getFromAllRoles(id);
        model.addAttribute("userFromAllRoles",userFromAllRoles);

        return "users/userFromAllRoles";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable int id, Model model){
        User user = super.get(id);
        model.addAttribute("user",user);

        return "users/user";
    }

    @GetMapping("/getBetweenRegistered/{startDate}/{endDate}")
    public String getBetweenRegisteredUser(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        super.getBetweenRegistered(startDate,endDate);

        return "users/users";
    }

    @RequestMapping(value = "/filterU", method = RequestMethod.GET)
    public String filterU(@ModelAttribute("user") User user, Model model){
        model.addAttribute("users", super.filterUser(user));

        return "users/users";
    }

    @PostMapping("/filterCondition")
    public ModelAndView filterConditionU(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("users/users");

        StringBuilder sqlCondition = new StringBuilder();
        //filter by TaxiUserOrder
        var id = ParseUtil.parseInteger(request.getParameter("id"));
        var name = ParseUtil.parseString(request.getParameter("name"));
        var email = ParseUtil.parseString(request.getParameter("email"));
        var password = ParseUtil.parseString(request.getParameter("password"));
        var phone = ParseUtil.parseString(request.getParameter("phone"));
        var registered = DateTimeUtil.parseLocalDate(request.getParameter("registered"));
        var enabled = request.getParameter("enabled");
        //filter by Condition
        var registeredFrom = DateTimeUtil.parseLocalDate(request.getParameter("registeredFrom"));
        var registeredTo = DateTimeUtil.parseLocalDate(request.getParameter("registeredTo"));

        User user = new User();
        if (id != null)
            user.setId(id);
        if (name != null)
            user.setName(name);
        if (email != null)
            user.setEmail(email);
        if (password != null)
            user.setPassword(password);
        if (phone != null)
            user.setPhone(phone);
        if (registered != null)
            user.setRegistered(registered);
        if ("on".equals(enabled))
            user.setEnabled(true);
        else if (enabled == null)
            user.setEnabled(false);

        if ((registeredFrom != null) && (registeredTo != null))
            sqlCondition.append(" u.registered between "+"\'"+registeredFrom+"\'"+" and "+"\'"+registeredTo+"\'");

        modelAndView.addObject("users",super.filterConditionUser(user, String.valueOf(sqlCondition)));

        return modelAndView;
    }




}
