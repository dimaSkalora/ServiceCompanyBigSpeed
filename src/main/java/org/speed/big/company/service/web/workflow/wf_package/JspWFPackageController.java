package org.speed.big.company.service.web.workflow.wf_package;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.speed.big.company.service.util.DateTimeUtil.parseLocalDate;
import static org.speed.big.company.service.util.DateTimeUtil.parseLocalDateTime;
import static org.speed.big.company.service.util.ParseUtil.parseInteger;
import static org.speed.big.company.service.util.ParseUtil.parseString;

@Controller
@RequestMapping("wfPackages")
public class JspWFPackageController extends AbstractWFPackageController{
    @RequestMapping
    public String wfPackages(Model model){
        model.addAttribute("wfPackages", super.getAll());
        return "workflow/wfPackages/wfPackages";
    }

    @RequestMapping(value = "/wfPackage", method = RequestMethod.GET)
    public ModelAndView wfPackage(){
        ModelAndView modelAndView = new ModelAndView("workflow/wfPackages/wfPackage");
        modelAndView.addObject("wfPackage", new WFPackage());
        modelAndView.addObject("allWFS", super.getAllWFS());
        modelAndView.addObject("allWFPS", super.getAllWFPS());

        return modelAndView;
    }

    @GetMapping("/wfPackageFilter")
    public String wfPackageFilter(){
        return "workflow/wfPackages/wfPackageFilter";
    }

    @RequestMapping(value = "/createRequestParam",method = RequestMethod.POST)
    public String createRequestParam(@RequestParam String name, @RequestParam LocalDate dateRegistration,
                                     @RequestParam  String customerName, @RequestParam String customerAddress,
                                     @RequestParam(required = false)  String customerAddressJur, @RequestParam String customerPhone,
                                     @RequestParam(required = false)  String customerEmail, @RequestParam String contractNumber,
                                     @RequestParam(required = false)  String description, @RequestParam String userAdd,
                                     @RequestParam LocalDateTime dateAdd, @RequestParam String userEdit,
                                     @RequestParam  LocalDateTime dateEdit, @RequestParam Integer wfServiceId,
                                     @RequestParam  Integer wfPackageStatusId){

        //createRequestParam?name=testName?dateRegistration=...

        WFService wfService = super.getWFS(wfServiceId);
        WFPackageStatus wfPackageStatus = super.getWFPS(wfPackageStatusId);

        WFPackage wfPackage = new WFPackage();
        wfPackage.setName(name);
        wfPackage.setDateRegistration(dateRegistration);
        wfPackage.setCustomerName(customerName);
        wfPackage.setCustomerAddress(customerAddress);
        wfPackage.setCustomerAddressJur(customerAddressJur);
        wfPackage.setCustomerPhone(customerPhone);
        wfPackage.setCustomerEmail(customerEmail);
        wfPackage.setContractNumber(contractNumber);
        wfPackage.setDescription(description);
        wfPackage.setUserAdd(userAdd);
        wfPackage.setUserEdit(userEdit);
        wfPackage.setDateAdd(dateAdd);
        wfPackage.setDateEdit(dateEdit);
        wfPackage.setWfServiceId(wfService);
        wfPackage.setWfPackageStatusId(wfPackageStatus);

        super.create(wfPackage);

        return "redirect:/wfPackages";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){

        var name = parseString(request.getParameter("name"));
        var dateRegistration = parseLocalDate(request.getParameter("dateRegistration"));
        var customerName = parseString(request.getParameter("customerName"));
        var customerAddress = parseString(request.getParameter("customerAddress"));
        var customerAddressJur = parseString(request.getParameter("customerAddressJur"));
        var customerPhone = parseString(request.getParameter("customerPhone"));
        var customerEmail = parseString(request.getParameter("customerEmail"));
        var contractNumber = parseString(request.getParameter("contractNumber"));
        var description = parseString(request.getParameter("description"));
        var userAdd = parseString(request.getParameter("userAdd"));
        var userEdit = parseString(request.getParameter("userEdit"));
        var dateAdd = parseLocalDateTime(request.getParameter("dateAdd"));
        var dateEdit = parseLocalDateTime(request.getParameter("dateEdit"));
        var wfServiceId = parseInteger(request.getParameter("wfServiceId"));
        var wfPackageStatusId = parseInteger(request.getParameter("wfPackageStatusId"));

        WFService wfService = super.getWFS(wfServiceId);
        WFPackageStatus wfPackageStatus = super.getWFPS(wfPackageStatusId);

        WFPackage wfPackage = new WFPackage();
        wfPackage.setName(name);
        wfPackage.setDateRegistration(dateRegistration);
        wfPackage.setCustomerName(customerName);
        wfPackage.setCustomerAddress(customerAddress);
        wfPackage.setCustomerAddressJur(customerAddressJur);
        wfPackage.setCustomerPhone(customerPhone);
        wfPackage.setCustomerEmail(customerEmail);
        wfPackage.setContractNumber(contractNumber);
        wfPackage.setDescription(description);
        wfPackage.setUserAdd(userAdd);
        wfPackage.setUserEdit(userEdit);
        wfPackage.setDateAdd(dateAdd);
        wfPackage.setDateEdit(dateEdit);
        wfPackage.setWfServiceId(wfService);
        wfPackage.setWfPackageStatusId(wfPackageStatus);

        super.create(wfPackage);

        return "redirect:/wfPackages";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute WFPackage wfPackage){
        if (wfPackage.isNew())
            super.create(wfPackage);
        else
            super.update(wfPackage);
        return "redirect:/wfPackages";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("wfPackage", super.get(id));
        model.addAttribute("allWFS", super.getAllWFS());
        model.addAttribute("allWFPS", super.getAllWFPS());

        return "workflow/wfPackages/wfPackage";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getWFP(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfPackages/wfPackage");
        modelAndView.addObject("wfPackage", super.get(id));

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteWFP(@RequestParam int id){
        //delete?id=000000000
        super.delete(id);

        return "redirect:/wfPackages";
    }

    @PostMapping("/filter")
    public ModelAndView filter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("workflow/wfPackages/wfPackages");

        var id = parseInteger(request.getParameter("id"));
        var name = parseString(request.getParameter("name"));
        var dateRegistration = parseLocalDate(request.getParameter("dateRegistration"));
        var customerName = parseString(request.getParameter("customerName"));
        var customerAddress = parseString(request.getParameter("customerAddress"));
        var customerAddressJur = parseString(request.getParameter("customerAddressJur"));
        var customerPhone = parseString(request.getParameter("customerPhone"));
        var customerEmail = parseString(request.getParameter("customerEmail"));
        var contractNumber = parseString(request.getParameter("contractNumber"));
        var description = parseString(request.getParameter("description"));
        var userAdd = parseString(request.getParameter("userAdd"));
        var userEdit = parseString(request.getParameter("userEdit"));
        var dateAdd = parseLocalDateTime(request.getParameter("dateAdd"));
        var dateEdit = parseLocalDateTime(request.getParameter("dateEdit"));
        var wfServiceId = parseInteger(request.getParameter("wfServiceId"));
        var wfPackageStatusId = parseInteger(request.getParameter("wfPackageStatusId"));

        WFService wfService = super.getWFS(wfServiceId);
        WFPackageStatus wfPackageStatus = super.getWFPS(wfPackageStatusId);

        WFPackage wfPackage = new WFPackage();
        if (id != null)
            wfPackage.setId(id);
        if (name != null)
            wfPackage.setName(name);
        if (dateRegistration != null)
            wfPackage.setDateRegistration(dateRegistration);
        if (customerName != null)
            wfPackage.setCustomerName(customerName);
        if (customerAddress != null)
            wfPackage.setCustomerAddress(customerAddress);
        if (customerAddressJur != null)
            wfPackage.setCustomerAddressJur(customerAddressJur);
        if (customerPhone != null)
            wfPackage.setCustomerPhone(customerPhone);
        if (customerEmail != null)
            wfPackage.setCustomerEmail(customerEmail);
        if (contractNumber != null)
            wfPackage.setContractNumber(contractNumber);
        if (description != null)
            wfPackage.setDescription(description);
        if (userAdd != null)
            wfPackage.setUserAdd(userAdd);
        if (userEdit != null)
            wfPackage.setUserEdit(userEdit);
        if (dateAdd != null)
            wfPackage.setDateAdd(dateAdd);
        if (dateEdit != null)
            wfPackage.setDateEdit(dateEdit);
        if (wfService != null)
            wfPackage.setWfServiceId(wfService);
        if (wfPackageStatus != null)
            wfPackage.setWfPackageStatusId(wfPackageStatus);

        modelAndView.addObject("wfPackages",super.filter(wfPackage));

        return modelAndView;
    }

    @GetMapping("/getData/{id}")
    public ModelAndView getData(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("workflow/wfPackages/wfPackageData");
        WFPackage wfPackage = super.get(id);
        modelAndView.addObject("wfpData", wfPackage);
        modelAndView.addObject("wfpWFService", super.getWFS(wfPackage.getWfServiceId().getId()));
        modelAndView.addObject("wfpWFPackageStatus", super.getWFPS(wfPackage.getWfPackageStatusId().getId()));

        return modelAndView;
    }
}
