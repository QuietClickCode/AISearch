package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.EmpList;
import com.zjj.aisearch.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: AISearch
 * @description: 查询
 * @author: zjj
 * @create: 2019-10-05 02:46:49
 **/
@Controller
@Slf4j
public class DemoController {

    @Autowired
    private DemoService demoServiceImpl;

    @RequestMapping("view")
    @ResponseBody
    public ModelAndView queryEmpList(ModelAndView modelAndView) {

        List<EmpList> empLists = demoServiceImpl.queryEmpList();
        modelAndView.addObject("empLists", empLists);
        modelAndView.setViewName("view");
        return modelAndView;
    }

    @RequestMapping("toadd")
    @ResponseBody
    public ModelAndView add(@RequestBody EmpList empList, ModelAndView modelAndView) {

        return modelAndView;
    }

}
