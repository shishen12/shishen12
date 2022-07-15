package com.qfedu.ioc.bean;

public class Book {
    private String name;
    private int bookId;

    public void init(){
        System.out.println("............init");
        this.bookId=1;
        this.name="sdsd";
    }
    public void destory(){
        System.out.println("...........destory");
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}
