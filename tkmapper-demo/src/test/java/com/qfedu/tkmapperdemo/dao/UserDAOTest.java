package com.qfedu.tkmapperdemo.dao;


import com.qfedu.tkmapperdemo.TkmapperDemoApplication;
import com.qfedu.tkmapperdemo.beans.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TkmapperDemoApplication.class)
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;
    @Test
    public void test(){
        User user=new User();
        user.setUsername("aaaaa");
        user.setPassword("11111");
        user.setUserImg("img/default.png");
        user.setUserRegtime(new Date());
        user.setUserModtime(new Date());
        int i = userDAO.insert(user);
    }
}
