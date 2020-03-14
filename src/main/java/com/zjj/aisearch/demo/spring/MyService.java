package com.zjj.aisearch.demo.spring;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-03-14 16:11:16
 **/
@Component("myservice")
public class MyService {

    public void say(String s) {
        System.out.println(s);
    }
}
