package com.zjj.aisearch.demo.tomcat;

import java.io.IOException;


public class MyBlog extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("Hello, this is my blog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("Hello, this is my blog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}