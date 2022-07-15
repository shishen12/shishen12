package com.qfedu.ioc.bean.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test3 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String classPath="com.qfedu.ioc.Student";

        Class<?> c=Class.forName(classPath);
        Object obj=c.newInstance();

        Field[] fields=c.getDeclaredFields();
        for (Field f:fields){
            String fieldName=f.getName();
            String setMethodName="set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            if("stuNum".equals(fieldName)){

            }
        }
    }
}
