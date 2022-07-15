package com.qfedu.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class TestController {
    public String execute(){
        System.out.println("..........execute");
        return "/tips.jsp";
    }
}
