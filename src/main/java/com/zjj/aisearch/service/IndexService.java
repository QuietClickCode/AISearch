package com.zjj.aisearch.service;

import com.zjj.aisearch.model.*;

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
    int insertMovie(Movie movie);
}
