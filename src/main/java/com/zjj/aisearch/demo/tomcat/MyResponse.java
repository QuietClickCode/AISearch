package com.zjj.aisearch.demo.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @program: AISearch
 * @description: 自己写的响应
 * @author: zjj
 * @create: 2020-02-28 10:46:13
 **/
public class MyResponse {
    private OutputStream outputStream;
    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("HTTP/1.1 200 OK\n")      //按照HTTP响应报文的格式写入
                .append("Content-Type:text/html\n")
                .append("\r\n")
                .append("<html><head><link rel=\"icon\" href=\"data:;base64,=\"></head><body>")
                .append(content)          //将页面内容写入
                .append("</body></html>");
        byte[] bytes = stringBuffer.toString().getBytes();
        outputStream.write(bytes);
        outputStream.close();

    }
}
