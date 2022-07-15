package com.qfedu.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Date;

@Component("stu")
@Scope("prototype")
//@Lazy(true)

public class Student {
    private String stuNum;
    private String stuName;
    private String stuGender;
    private int stuAge;
    private Date enterenceTime;

//  byType
    @Autowired
//  @Resource(byName)
    private  Clazz clazz;

    @Override
    public String toString() {
        return "Student{" +
                "stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", stuAge=" + stuAge +
                ", enterenceTime=" + enterenceTime +
                ", clazz=" + clazz +
                '}';
    }

    @PostConstruct
    public  void init(){
        System.out.println("......init");
    }
    @PreDestroy
    public void destory(){
        System.out.println("......destroy");
    }
}
 