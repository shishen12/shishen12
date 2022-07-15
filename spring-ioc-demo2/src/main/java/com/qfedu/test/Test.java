package com.qfedu.test;

import com.qfedu.beans.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        Student stu = (Student)context.getBean("stu");
        Student stu1 = (Student)context.getBean("stu");
        System.out.println(stu);
        System.out.println(stu1);
    }
}
