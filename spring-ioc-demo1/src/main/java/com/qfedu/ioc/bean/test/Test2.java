package com.qfedu.ioc.bean.test;

import com.qfedu.ioc.bean.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student1 = (Student) context.getBean("student");

        System.out.println(student1);

        Student student2 = (Student)context.getBean("stu2");

        System.out.println(student2);

    }
}
