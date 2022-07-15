package com.qfedu.test;

import com.qfedu.dao.BookDAOImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test3 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDAOImpl bookDAO=(BookDAOImpl) context.getBean("bookDAO");
        bookDAO.update();
    }
}
