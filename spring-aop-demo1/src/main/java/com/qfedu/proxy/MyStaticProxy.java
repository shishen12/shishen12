package com.qfedu.proxy;

public class MyStaticProxy  {

    private GenaralDAO genaralDAO;

    public MyStaticProxy(GenaralDAO genaralDAO){
        this.genaralDAO=genaralDAO;
    }

    public void insert(){
       begin();
       genaralDAO.insert();
       commit();
    }

    public void delete(){
        begin();
        genaralDAO.delete();
        commit();
    }

    public void update(){
        begin();
        genaralDAO.update();
        commit();
    }

    long time1;
    long time2;
    public void begin(){
        time1=System.currentTimeMillis();
        System.out.println("开启事务");
    }

    public void commit(){
        System.out.println("提交事务");
        time2=System.currentTimeMillis();
        System.out.println(genaralDAO.getClass()+"执行时间："+(time2-time1));
    }
}
