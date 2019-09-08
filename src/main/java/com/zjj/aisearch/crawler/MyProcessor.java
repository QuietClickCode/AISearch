package com.zjj.aisearch.crawler;

import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateConvert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 2019年3月9日22:14:19
 */
@Component
public class MyProcessor implements PageProcessor {
    @Autowired
    private IndexService indexService;


    public void process(Page page) {


        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{8}").all());
        page.putField("title", page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1"));
        page.putField("content", page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]"));

        Article article = new Article();
        article.setContent(page.getResultItems().get("content") == null ? "" : page.getResultItems().get("content").toString());
        article.setTitle(page.getResultItems().get("title") == null ? "" : page.getResultItems().get("title").toString());
        article.setCreateTime(DateConvert.getTime());

        indexService.insertArticle(article);


    }

    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }

    @Test
    public void run() {
        Spider.create(this).addUrl("https://blog.csdn.net/").run();
    }
}


