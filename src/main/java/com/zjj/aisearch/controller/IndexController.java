package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.model.Item;
import com.zjj.aisearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-21 19:16:26
 **/
@Controller
public class IndexController {

    @Autowired
    private IndexService indexServiceImpl;

    private HashMap<String,String> map = new HashMap<>();

    @RequestMapping("/search")
    @ResponseBody
    public List<Article> search(String keyword) {
        List<Article> articles = indexServiceImpl.search(keyword);
        return articles;

    }

    @RequestMapping("/searchItem")
    @ResponseBody
    public List<Item> searchItem(String keyword) {
        if (!keyword.isEmpty()) {
            List<Item> items = indexServiceImpl.searchItem(keyword);
            return items;
        }
        return null;

    }

    @RequestMapping("/todetail")
    public String toDetail(String keyword, RedirectAttributes attributes) {
        if (!keyword.isEmpty()) {
            System.out.println(keyword);
            map.put("keyword", keyword);
            return "redirect:detail";
        }
        return null;
    }

    @RequestMapping("/detail")
    public String detail() {
        return "detail";
    }

    @RequestMapping("/json")
    @ResponseBody
    public List<Item> json() {
        List<Item> items = indexServiceImpl.searchItem(map.get("keyword"));
        return items;
    }



}
