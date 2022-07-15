package com.qfedu.service.impl;

import com.qfedu.bean.User;
import com.qfedu.dao.UserDAO;
import com.qfedu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public User checkLogin(String userName, String userPwd) {
        User user=userDAO.queryUserByName(userName);

        if(user.getUserPwd().equals(userPwd)){
            return user;
        }else{
            return null;
        }
    }
}
