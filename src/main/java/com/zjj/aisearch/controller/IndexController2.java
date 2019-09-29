package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateConvert;
import com.zjj.aisearch.utils.HTMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class IndexController2 implements PageProcessor {

    @Autowired
    private IndexService indexService;



    @RequestMapping("jianshu")
    @ResponseBody
    public void jianshu(String keyword) {
        Spider.create(this).addUrl("https://www.jianshu.com").thread(3).run();
    }



    @Override
    public void process(Page page) {
        //简书
        page.addTargetRequests(page.getHtml().links().regex("https://www.jianshu.com/p/[a-z 0-9]{12}").all());
        String title = page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div/section[1]/h1").get();
        String content = page.getHtml().xpath("//*[@id=\"__next\"]/div[1]/div/div[1]/section[1]/article").get();
        if (title != null && content != null && title != "" && content != "") {
            page.putField("title", HTMLUtil.delHTMLTag(title));
            page.putField("content", HTMLUtil.delHTMLTag(content));
            Article article = new Article();
            article.setContent(page.getResultItems().get("content") == null ? "" : page.getResultItems().get("content").toString());
            article.setTitle(page.getResultItems().get("title") == null ? "" : page.getResultItems().get("title").toString());
            article.setCreateTime(DateConvert.getTime());
            this.indexService.insertArticle(article);
            System.out.println(article.getId());
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(1000).setRetryTimes(1);
    }
}
