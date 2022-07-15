package com.qfedu.ioc.bean.test;

import com.qfedu.ioc.bean.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test4 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("........................");
        Book book1=(Book)context.getBean("book");
        Book book2=(Book)context.getBean("book");
        System.out.println(book1);
        System.out.println(book2);
    }
}
