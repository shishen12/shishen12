package com.qfedu.springboot.demo.service.impl;

import com.qfedu.springboot.demo.dao.UserDAO;
import com.qfedu.springboot.demo.entity.User;
import com.qfedu.springboot.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;


    public User userRegist(User user) {
        int i = userDAO.insertUser(user);

        if(i>0){
            return user;
        }else {
            return null;
        }
    }
}
