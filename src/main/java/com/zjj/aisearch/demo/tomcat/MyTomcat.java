package com.zjj.aisearch.demo.tomcat;

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

    private Map<String, String> urlServletMapping = new HashMap<>();

    public MyTomcat(Integer port) {
        this.port = port;
    }

    public void start() throws Exception, IllegalAccessException, InstantiationException, ClassNotFoundException {


        initServletMapping();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("MyTomcat is Starting...");
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            MyRequest myRequest = new MyRequest(inputStream);
            MyResponse myResponse = new MyResponse(outputStream);
            dispatch(myRequest, myResponse);
            accept.close();
        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) throws Exception, IllegalAccessException, InstantiationException {
        String s = urlServletMapping.get(myRequest.getUrl());
        Class myServletClass = Class.forName(s);
        MyServlet myservlet = (MyServlet) myServletClass.newInstance();
        myservlet.service(myRequest, myResponse);
       /* Class service = Class.forName("com.zjj.aisearch.demo.spring.SpringTestService");
        SpringTestService springTestService = (SpringTestService) service.newInstance();
        Field springTestService2 = myServletClass.getField("name");
        System.out.println(springTestService2.get(myServletClass));*/

    }

    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMapping.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    public static void main(String[] args) throws Exception, IllegalAccessException, ClassNotFoundException, InstantiationException {
        MyTomcat myTomcat = new MyTomcat(8080);
        myTomcat.start();

    }
}
