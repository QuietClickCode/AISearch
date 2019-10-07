package com.zjj.aisearch.controller;

import com.zjj.aisearch.dao.CSDNArticleRepository;
import com.zjj.aisearch.model.CSDNArticle;
import com.zjj.aisearch.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("csdn")
public class CSDNController {
 
    @Autowired
    private CSDNArticleRepository csdnArticleRepository;
 
    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    public String add() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        CSDNArticle csdnArticle = new CSDNArticle();
        CSDNArticle query;
        String sql = null;
        for (int i = 1; i <= 101268; i++) {
            sql = "select * from article where id=?";
            query = qr.query(sql, new BeanHandler<CSDNArticle>(CSDNArticle.class), i);
            if (query != null) {
                csdnArticle.setId(query.getId());
                csdnArticle.setTitle(query.getTitle());
                csdnArticle.setContent(query.getContent());
                csdnArticle.setCreatetime(query.getCreatetime());
                csdnArticleRepository.save(csdnArticle);
            }
        }
        return "success";
    }
 
}