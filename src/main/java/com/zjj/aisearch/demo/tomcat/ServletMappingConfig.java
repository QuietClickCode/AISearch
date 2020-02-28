package com.zjj.aisearch.demo.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: AISearch
 * @description: Servlet, Url映射
 * @author: zjj
 * @create: 2020-02-28 10:54:24
 **/
public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("index", "/index", "com.zjj.aisearch.demo.tomcat.IndexServlet"));
        servletMappingList.add(new ServletMapping("myblog", "/myblog", "com.zjj.aisearch.demo.tomcat.MyBlog"));
        servletMappingList.add(new ServletMapping("testspring", "/testspring", "com.zjj.aisearch.demo.spring.SpringTestController"));
    }
}
