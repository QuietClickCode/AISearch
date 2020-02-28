package com.zjj.aisearch.demo.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: AISearch
 * @description: 自己写的tomcat
 * @author: zjj
 * @create: 2020-02-28 10:20:33
 **/
public class MyTomcat {
    private Integer port = 8080;

    private Map<String,String> urlServletMapping = new HashMap<>();

    public MyTomcat(Integer port) {
        this.port = port;
    }

    public void start() throws IOException {
        initServletMapping();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("MyTomcat is Starting...");
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            MyRequest myRequest = new MyRequest();
            MyResponse myResponse = new MyResponse();
            dispatch(myRequest, myResponse);
            accept.close();
        }
    }

    private void initServletMapping() {
    }

}
