package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.Todo;
import com.zjj.aisearch.service.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: AISearch
 * @description: get方法
 * @author: zjj
 * @create: 2019-12-04 20:47:48
 **/
@RestController
public class GetController {

    @Autowired
    private GetService getServiceImpl;

    @GetMapping("/todoList")
    public List<Todo> getTodoList(Map<String, String> map) {
        return getServiceImpl.getTodoList(map);
    }

}
