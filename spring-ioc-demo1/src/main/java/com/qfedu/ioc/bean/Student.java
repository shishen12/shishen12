package com.qfedu.ioc.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {
        private  String stuNum;
        private  String stuName;
        private  String stuGender;
        private  String stuAge;
        private  Date enterenceTime;
        private  Clazz clazz;
        public List<String> hobbies;
        public Set<String> sets;
        public Map<String,Object> maps;


    public Set<String> getSets() {
        return sets;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Student() {
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public Date getEnterenceTime() {
        return enterenceTime;
    }

    public void setEnterenceTime(Date enterenceTime) {
        this.enterenceTime = enterenceTime;
    }


    @Override
    public String toString() {
        return "Student{" +
                "stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", stuAge='" + stuAge + '\'' +
                ", enterenceTime=" + enterenceTime +
                ", clazz=" + clazz +
                ", hobbies=" + hobbies +
                ", sets=" + sets +
                ", maps=" + maps +
                '}';
    }
}
