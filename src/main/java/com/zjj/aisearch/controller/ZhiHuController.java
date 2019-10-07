package com.zjj.aisearch.controller;

import com.zjj.aisearch.dao.ZhiHuArticleRepository;
import com.zjj.aisearch.model.ZhiHuArticle;
import com.zjj.aisearch.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("zh")
public class ZhiHuController {
 
    @Autowired
    private ZhiHuArticleRepository zhiHuArticleRepository;

    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    public String add() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        ZhiHuArticle zhiHuArticle = new ZhiHuArticle();
        ZhiHuArticle query;
        String sql = null;
        for (int i = 1; i <= 150719; i++) {
            sql = "select * from zhihu_article where id=?";
            query = qr.query(sql, new BeanHandler<ZhiHuArticle>(ZhiHuArticle.class), i);
            if (query != null) {
                zhiHuArticle.setId(query.getId());
                zhiHuArticle.setQuestion_info(query.getQuestion_info());
                zhiHuArticle.setQuestion(query.getQuestion());
                zhiHuArticle.setAnswer(query.getAnswer());
                zhiHuArticle.setCreatetime(query.getCreatetime());
                zhiHuArticleRepository.save(zhiHuArticle);
            }
        }
        return "success";
    }
 
}