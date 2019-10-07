package com.zjj.aisearch.controller;

import com.zjj.aisearch.dao.JianShuArticleRepository;
import com.zjj.aisearch.model.JianShuArticle;
import com.zjj.aisearch.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

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
    public String add() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        JianShuArticle jianShuArticle = new JianShuArticle();
        JianShuArticle query;
        String sql = null;
        for (int i = 2439; i <= 65277; i++) {
            sql = "select * from jianshu_article where id=?";
            query = qr.query(sql, new BeanHandler<JianShuArticle>(JianShuArticle.class), i);
            if (query != null) {
                jianShuArticle.setId(query.getId());
                jianShuArticle.setTitle(query.getTitle());
                jianShuArticle.setContent(query.getContent());
                jianShuArticle.setCreatetime(query.getCreatetime());
                jianShuArticleRepository.save(jianShuArticle);
            }
        }
        return "success";
    }
 
}