package com.qfedu.dao;

import com.qfedu.proxy.GenaralDAO;

public class StudentDAOImpl implements GenaralDAO {

    public void insert(){
        System.out.println("insert--student");
    }

    public void delete(){
        System.out.println("delete--student");
    }

    public void update(){
        System.out.println("update--student");
    }
}
