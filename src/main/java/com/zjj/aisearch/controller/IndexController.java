package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.model.Item;
import com.zjj.aisearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-21 19:16:26
 **/
@RestController
public class IndexController {

    @Autowired
    private IndexService indexServiceImpl;

    @RequestMapping("/search")
    @ResponseBody
    public List<Article> search(String keyword) {
        List<Article> articles = indexServiceImpl.search(keyword);
        return articles;

    }

    @RequestMapping("/searchItem")
    @ResponseBody
    public List<Item> searchItem(String keyword) {
        List<Item> items = indexServiceImpl.searchItem(keyword);
        return items;

    }

}
