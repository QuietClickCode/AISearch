package com.zjj.aisearch.service;

import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.model.SearchRecord;
import com.zjj.aisearch.model.User;
import com.zjj.aisearch.model.ZhihuArticle;

/**
 * @program: aisearch
 * @description: service
 * @author: zjj
 * @create: 2019-09-07 17:16:18
 **/
public interface IndexService {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);
    int insertArticle(Article article);
    int insertZhihuArticle(ZhihuArticle article);
}
