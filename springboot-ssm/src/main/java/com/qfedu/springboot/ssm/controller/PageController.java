package com.qfedu.springboot.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/test.html")
    public String test(){
        return "test";
    }
}
