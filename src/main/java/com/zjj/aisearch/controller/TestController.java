package com.zjj.aisearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: AISearch
 * @description: 查询
 * @author: zjj
 * @create: 2019-10-05 02:46:49
 **/
@Controller
@Slf4j
public class TestController {

    @RequestMapping("user/add")
    @ResponseBody
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName("user/add");
        return modelAndView;
    }
    @RequestMapping("user/update")
    @ResponseBody
    public ModelAndView update(ModelAndView modelAndView) {
        modelAndView.setViewName("user/update");
        return modelAndView;
    }

    @RequestMapping("test")
    @ResponseBody
    public ModelAndView test(ModelAndView modelAndView) {
        modelAndView.setViewName("test");
        return modelAndView;
    }
}
