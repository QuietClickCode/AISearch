package com.zjj.aisearch.controller;

import com.zjj.aisearch.dao.BKYArticleRepository;
import com.zjj.aisearch.model.BKYArticle;
import com.zjj.aisearch.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("bky")
public class BKYController {
 
    @Autowired
    private BKYArticleRepository bkyArticleRepository;
 
    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    public String add() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        BKYArticle bkyArticle = new BKYArticle();
        BKYArticle query;
        String sql = null;
        for (int i = 1; i <= 11951; i++) {
            sql = "select * from bky_article where id=?";
            query = qr.query(sql, new BeanHandler<BKYArticle>(BKYArticle.class), i);
            if (query != null) {
                bkyArticle.setId(query.getId());
                bkyArticle.setTitle(query.getTitle());
                bkyArticle.setContent(query.getContent());
                bkyArticle.setCreatetime(query.getCreatetime());
                bkyArticleRepository.save(bkyArticle);
            }
        }
        return "success";
    }
 
}