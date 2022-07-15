package com.qfedu.test;

import com.qfedu.dao.BookDAOImpl;
import com.qfedu.proxy.CGLibDynamicProxy;
import com.qfedu.proxy.GenaralDAO;
import com.qfedu.proxy.JDKDynamicProxy;

public class TestDynamicProxy {
    public static void main(String[] args) {
        BookDAOImpl bookDAO=new BookDAOImpl();

        GenaralDAO proxy=(GenaralDAO) new JDKDynamicProxy(bookDAO).getProxy();
        proxy.delete();


        CGLibDynamicProxy cgLibDynamicProxy=new CGLibDynamicProxy(bookDAO);
        BookDAOImpl proxy1=(BookDAOImpl)cgLibDynamicProxy.getProxy();
        proxy1.update();
    }
}
