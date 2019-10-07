package com.zjj.aisearch.controller;

import com.zjj.aisearch.dao.JianShuArticleRepository;
import com.zjj.aisearch.model.JianShuArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("js")
public class JianShuController {
 
    @Autowired
    private JianShuArticleRepository jianShuArticleRepository;
 
    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    public String add() {
        JianShuArticle jianShuArticle = new JianShuArticle();
        jianShuArticle.setId(1);
        jianShuArticle.setTitle("haha");
        jianShuArticle.setContent("dd");
        jianShuArticleRepository.save(jianShuArticle);
        System.err.println("add a obj");
        return "success";
    }
 
}