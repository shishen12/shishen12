package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.xml.transform.Source;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private DataSource dataSource;

    @Test
    public void contextLoads() {
        System.out.println(dataSource);
    }

}
