package com.zjj.aisearch.demo.tomcat;

/**
 * @program: AISearch
 * @description: 我的Servlet
 * @author: zjj
 * @create: 2020-02-28 10:49:41
 **/
public abstract class MyServlet {
    public void service(MyRequest myRequest,MyResponse myResponse) {
        if (myRequest.getMethod().equalsIgnoreCase("POST")) {
            doPost(myRequest,myResponse);
        } else if(myRequest.getMethod().equalsIgnoreCase("GET")) {
            doGet(myRequest, myResponse);
        }
    }

    public void doGet(MyRequest myRequest, MyResponse myResponse) {
    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) {

    }
}
