package com.qfedu.controller;


import com.qfedu.beans.Book;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/book")
public class BookController {
    @RequestMapping("/add")
    public String addBook(Book book, MultipartFile imgFile, HttpServletRequest request) throws IOException {
        System.out.println("..........add");
        String originalFilename = imgFile.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName=System.currentTimeMillis()+ext;
        String dir =request.getServletContext().getRealPath("imgs");
        String savePath=dir+"/"+fileName;
        imgFile.transferTo(new File(savePath));
        book.setBookImg("imgs/"+fileName);

        return "/tips.jsp";
    }
    @RequestMapping("/list")
    @ResponseBody
    public String[] listImgs(HttpServletRequest request){
        String dir = request.getServletContext().getRealPath("imgs");
        File imgDir=new File(dir);
        String[] fileNames = imgDir.list();
        return fileNames;
    }

    @RequestMapping("/download")
    public void downloadImg(String fname, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dir=request.getServletContext().getRealPath("imgs");
        String filePath=dir+"/"+fname;
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(filePath);
        response.setContentType("application/exe");
        response.addHeader("content-disposition","attachment;filename="+fname);
        IOUtils.copy(fileInputStream,response.getOutputStream());



    }

    @RequestMapping("/query")
    public String query(String bookId){
        System.out.println(bookId.length());
        int bid=Integer.parseInt(bookId);
        return "/tips.jsp";
    }
}
