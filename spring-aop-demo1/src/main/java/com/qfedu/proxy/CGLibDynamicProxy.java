package com.qfedu.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibDynamicProxy implements MethodInterceptor {

    private Object obj;

    public CGLibDynamicProxy(Object obj){
        this.obj=obj;
    }

    public Object getProxy(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxy=enhancer.create();
        return proxy;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        begin();
        Object returnValue=method.invoke(obj,objects);
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
