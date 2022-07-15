package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserDAO;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserDAO,User> implements UserService{

    @Resource
    private UserDAO userDAO;

    @Override
    public User checkLogin(String userName, String userPwd) {
        return null;
    }

    @Override
    public void selectUser(User user) {
        User u = this.baseMapper.selectOne(new QueryWrapper<User>()
                .eq("user_name", user.getName())
                .eq("user_pwd", user.getPassword()));

        this.baseMapper.selectList(ne)
        System.out.println(u);
    }
}
