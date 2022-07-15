package com.qfedu.springboot.demo.controller;

import com.qfedu.springboot.demo.entity.User;
import com.qfedu.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/regist")
    @ResponseBody
    public User regist(User user){
        return  userService.userRegist(user);
    }

}
