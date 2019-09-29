package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.model.SearchRecord;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateConvert;
import com.zjj.aisearch.utils.HTMLUtil;
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
        Spider.create(this).addUrl("https://www.jianshu.com").thread(1).run();
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
        /*page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{9}").all());*/
        page.addTargetRequests(page.getHtml().links().regex("https://www.jianshu.com/p/[a-z 0-9 -]+").all());
        /*dengyao-blogs/p/11606929.html*/
        /*page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/leixiaohua1020/article/details/18893769").all());*/
        System.out.println(page.toString() + "*********************************************");
        System.out.println(page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div/section[1]/h1") + "--------------------------------" + page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div[1]/section[1]/article"));
        System.out.println(!(page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div/section[1]/h1").equals("")));
        System.out.println(!(page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div/section[1]/h1")==null) && !(page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div[1]/section[1]/article")==null));
        if (!(page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div/section[1]/h1")==null) && !(page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div[1]/section[1]/article")==null)) {
            page.putField("title", HTMLUtil.delHTMLTag(page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div/section[1]/h1").toString()));
            page.putField("content", HTMLUtil.delHTMLTag(page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div[1]/section[1]/article").toString()));
        }
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
        return Site.me().setSleepTime(1000).setRetryTimes(1);
    }
}
