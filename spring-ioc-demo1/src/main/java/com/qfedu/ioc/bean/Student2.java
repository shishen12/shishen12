package com.qfedu.ioc.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student2 {
        private  String stuNum;
        private  String stuName;
        private  String stuGender;
        private  String stuAge;
        private Date enterenceTime;
        private  Clazz clazz;

    @Override
    public String toString() {
        return "Student2{" +
                "stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", stuAge='" + stuAge + '\'' +
                ", enterenceTime=" + enterenceTime +
                ", clazz=" + clazz +
                '}';
    }

    public Student2(String stuNum, String stuName, String stuGender, String stuAge, Date enterenceTime, Clazz clazz) {
        this.stuNum = stuNum;
        this.stuName = stuName;
        this.stuGender = stuGender;
        this.stuAge = stuAge;
        this.enterenceTime = enterenceTime;
        this.clazz = clazz;
    }
}
