package com.qfedu.redis.controller;


import com.qfedu.redis.beans.Product;
import com.qfedu.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public String testadd(){
        productService.addInfoToRedis();
        return "success";
    }

    @RequestMapping("/get")
    public Product testget(String pid){
        Product product=productService.getInfoFromRedis(pid);
        return product;
    }
}
