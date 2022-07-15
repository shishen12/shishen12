package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@Controller
public class TestController {

    @Autowired
    private DataSource dataSource;

    @ResponseBody
    @RequestMapping("/test1")
    public String test() {
        return "test";
    }
}
