package com.zjj.aisearch.crawler;

import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-09 03:40:43
 **/

@Component
public class MyPipeline implements Pipeline {
    @Autowired
    private IndexService indexService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("=========================================");
        Article article = new Article();
        article.setCreateTime(DateConvert.getTime());
        article.setTitle("dd");
        article.setContent("fjak");

        System.out.println(indexService);
        indexService.insertArticle(article);
    }
}
