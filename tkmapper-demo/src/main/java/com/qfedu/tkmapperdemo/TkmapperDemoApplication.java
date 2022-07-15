package com.qfedu.tkmapperdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.qfedu.tkmapperdemo.dao")
public class TkmapperDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkmapperDemoApplication.class, args);
    }

}
