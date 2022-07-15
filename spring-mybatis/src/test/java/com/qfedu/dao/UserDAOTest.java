package com.qfedu.dao;

import com.qfedu.bean.User;
import com.qfedu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;



public class UserDAOTest {

    @org.junit.Test
    public void queryUsers() {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
//        List<User> users=userDAO.queryUsers();
//        System.out.println(users);

        UserServiceImpl userService=(UserServiceImpl) context.getBean("userServiceImpl");
        List<User> users=userService.listUsers();
        System.out.println(users);

    }

    @Test
    public void  test1(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDAO userDAO=(UserDAO)context.getBean("userDAO");
        User user=new User(0,"sdsd","s2131","爱神的箭阿三","03.jpg");
        int i=userDAO.insertUser(user);
        System.out.println(i);
    }
}