package com.qfedu.controllers;


import com.qfedu.beans.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/test")
public class Test1Controller {

    @RequestMapping("/add")
    public String addBook(Book book){



        return "tips";
    }
}
