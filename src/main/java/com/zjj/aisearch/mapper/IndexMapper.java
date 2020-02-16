package com.zjj.aisearch.mapper;


import com.zjj.aisearch.model.*;

public interface IndexMapper  {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);
    int insertArticle(Article article);
    int insertZhihuArticle(ZhihuArticle article);

    int insertMovie(Movie movie);
}
