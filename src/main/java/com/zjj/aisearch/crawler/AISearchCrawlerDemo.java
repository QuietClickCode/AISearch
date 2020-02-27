package com.zjj.aisearch.crawler;

import org.springframework.web.client.RestTemplate;

public class AISearchCrawlerDemo {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.getForObject("http://localhost:8090/zhihuapi", String.class));
    }
}