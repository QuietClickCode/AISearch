package com.zjj.aisearch.mapper;


import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.model.SearchRecord;
import com.zjj.aisearch.model.User;
import com.zjj.aisearch.model.ZhihuArticle;

public interface IndexMapper  {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);
    int insertArticle(Article article);
    int insertZhihuArticle(ZhihuArticle article);

}
