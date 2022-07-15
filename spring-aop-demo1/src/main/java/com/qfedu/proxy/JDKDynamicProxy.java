package com.qfedu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy implements InvocationHandler {
    private Object obj;
    public JDKDynamicProxy(Object obj){
        this.obj=obj;
    }

    public  Object getProxy() {
        ClassLoader classLoader=obj.getClass().getClassLoader();
        Class<?>[] interfaces=obj.getClass().getInterfaces();



        Object o = Proxy.newProxyInstance(classLoader, interfaces,this);
        return o;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        begin();
        Object returnValue=method.invoke(obj);
        commit();
        return null;
    }

    public void begin(){

        System.out.println("开启事务");
    }

    public void commit(){
        System.out.println("提交事务");
    }
}
