package com.zjj.aisearch.demo.tomcat;

import java.io.IOException;


public class IndexServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("Hello, myTomcat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("Hello, myTomcat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}