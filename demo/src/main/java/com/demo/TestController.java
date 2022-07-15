package com.demo;

import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {

    public String test() {
        return "test";
    }
}
