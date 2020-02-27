package com.zjj.aisearch.zbblog;

import org.springframework.web.client.RestTemplate;

/**
 * @program: AISearch
 * @description: zb-blog 测试系统交互
 * @author: zjj
 * @create: 2020-02-27 12:30:32
 **/
public class ZbBlogDemo {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.getForObject("http://localhost:8090/zhihuapi", String.class));
    }
}
