package org.speed.big.company.service.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
