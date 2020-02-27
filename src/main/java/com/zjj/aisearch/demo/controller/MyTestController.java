package com.zjj.aisearch.demo.controller;

import com.zjj.aisearch.demo.annotations.ZjjRequestMapping;
import com.zjj.aisearch.service.impl.GetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: AISearch
 * @description: 测试Controller
 * @author: zjj
 * @create: 2020-02-27 23:45:30
 **/
@Controller
@RequestMapping
public class MyTestController {

    @Autowired
    private GetServiceImpl getService;

    @RequestMapping("/fkal")
    public static void test() {
        System.out.println("dddddddafnN多d");
    }

    @ResponseStatus
    @ResponseBody
    @ZjjRequestMapping("/dddd")
    public String test2() {
        System.out.println("ddddd");
        return "你好";
    }
}
