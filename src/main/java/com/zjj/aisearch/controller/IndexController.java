package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.model.SearchRecord;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @program: aisearch
 * @description: 入口
 * @author: zjj
 * @create: 2019-09-07 16:53:15
 **/
@Controller
public class IndexController implements PageProcessor {

    @Autowired
    private IndexService indexService;


    @RequestMapping("index")
    public String index(Model model, String keyword) {
        return "index";
    }

    @RequestMapping("search")
    @ResponseBody
    public void search(String keyword) {
        System.out.println(indexService + "----------------");
        Spider.create(this).addUrl("https://blog.csdn.net/").run();
        SearchRecord searchRecord = new SearchRecord();
        searchRecord.setKeyword(keyword);
        searchRecord.setSearchTime(DateConvert.getTime());

        int i = indexService.insertSearchRecord(searchRecord);
        System.out.println(i);

        Article article = new Article();
        article.setCreateTime(DateConvert.getTime());
        article.setTitle("dd");
        article.setContent("fjak");

        indexService.insertArticle(article);

    }


    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{8}").all());
        page.putField("title", page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1"));
        page.putField("content", page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]"));

        Article article = new Article();
        article.setContent(page.getResultItems().get("content") == null ? "" : page.getResultItems().get("content").toString());
        article.setTitle(page.getResultItems().get("title") == null ? "" : page.getResultItems().get("title").toString());
        article.setCreateTime(DateConvert.getTime());
        System.out.println(article.toString());

        System.out.println(this.indexService + "===========================");
        this.indexService.insertArticle(article);
        page.putField("article", article);
    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }
}
