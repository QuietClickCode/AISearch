package com.zjj.aisearch.demo.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: AISearch
 * @description: 自己封装的请求体
 * @author: zjj
 * @create: 2020-02-28 10:38:41
 **/
public class MyRequest {
    //请求路径
    private String url;
    private String method;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes))>0) {
            httpRequest = new String(httpRequestBytes,0,length);
        }
        String httpHead = httpRequest.split("\n")[0];
        System.out.println(httpHead);
        method = httpHead.split("\\s")[0];
        url = httpHead.split("\\s")[1];
        System.out.println(this.toString());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
