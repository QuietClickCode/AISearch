package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.User;
import com.zjj.aisearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: aisearch
 * @description: 入口
 * @author: zjj
 * @create: 2019-09-07 16:53:15
 **/
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;


    @RequestMapping("/index")
    public String index(Model model) {

        User user = indexService.index();
        System.out.println(user.toString());
        return "index";
    }
}
