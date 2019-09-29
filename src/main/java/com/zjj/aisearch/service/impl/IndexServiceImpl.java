package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.IndexMapper;
import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.model.SearchRecord;
import com.zjj.aisearch.model.User;
import com.zjj.aisearch.model.ZhihuArticle;
import com.zjj.aisearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: aisearch
 * @description:
 * @author: zjj
 * @create: 2019-09-07 17:17:08
 **/

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;

    @Override
    public User index() {
        return indexMapper.index();
    }

    @Override
    public int insertSearchRecord(SearchRecord searchRecord) {
        return indexMapper.insertSearchRecord(searchRecord);
    }

    @Override
    public int insertArticle(Article article) {
        return indexMapper.insertArticle(article);
    }

    @Override
    public int insertZhihuArticle(ZhihuArticle article) {
        return indexMapper.insertZhihuArticle(article);
    }
}
