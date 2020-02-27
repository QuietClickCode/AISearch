package com.zjj.aisearch.tbed;

import org.springframework.web.client.RestTemplate;

/**
 * @program: AISearch
 * @description: 测试图床远程调用
 * @author: zjj
 * @create: 2020-02-27 11:39:19
 **/
public class TbedDemo {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.getForObject("http://localhost:8083/user/register", String.class));
    }
}
