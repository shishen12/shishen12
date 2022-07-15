package com.qfedu.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.beans.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("book")
public class BookControllers {
    @RequestMapping("/add")
    public String addBook(String name, String author, double price, Model model){
        System.out.println(".....book_add");
        model.addAttribute("key1","value1");
        model.addAttribute("book",new Book(1,"撒大大","s二二",222));
        return "tips";
    }

    @RequestMapping("/add1")
    public ModelAndView addBook1(String name, String author, double price){

        ModelAndView modelAndView = new ModelAndView("tips");
        modelAndView.addObject("key1","value1");
        modelAndView.addObject("book",new Book(2,"sdddsd","sdssd",2222));
        return modelAndView;
    }





    @RequestMapping("/list")
    public void list(@RequestHeader("token") String token){
        System.out.println(".....book_list");
    }

    @RequestMapping("/update")
    @ResponseBody
    public List<Book> update(){
        System.out.println(".....book_update");
        List<Book> books=new ArrayList<>();
        books.add(new Book(1,"sdsd","sdsd",222));
        books.add(new Book(2,"sdddsd","sdssd",2222));
        return books;


//          通过response响应请求
//        String s = new ObjectMapper().writeValueAsString(book);
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.println(s);
//        out.flush();
//        out.close();


//         流响应请求
//        try {
//            ServletInputStream is=request.getInputStream();
//            StringBuffer buffer=new StringBuffer();
//            byte[] bs=new byte[100];
//            int len=-1;
//            while ((len= is.read(bs))!=-1){
//                    String s=new String(bs,0,len);
//                    buffer.append(s);
//            }
//            System.out.println(buffer.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
