package com.qfedu.ioc.bean.test;

import com.qfedu.ioc.servlets.TestServlet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test5 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        TestServlet testServlet = (TestServlet)context.getBean("testServlet");
        testServlet.doPost();
    }
}
