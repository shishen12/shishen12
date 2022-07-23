package com.example.redisdemo3.controller;




import com.example.redisdemo3.service.ProductService;
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
}
