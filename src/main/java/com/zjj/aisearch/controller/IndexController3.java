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
 * @description: 博客园入口
 * @author: zjj
 * @create: 2019-09-07 16:53:15
 **/
@Controller
public class IndexController3 implements PageProcessor {

    @Autowired
    private IndexService indexService;



    @RequestMapping("bky")
    @ResponseBody
    public void jianshu(String keyword) {
        Spider.create(this).addUrl("https://www.cnblogs.com/xwgblog/p/11703104.html").thread(3).run();
    }



    @Override
    public void process(Page page) {
        //bky
        page.addTargetRequests(page.getHtml().links().regex("https://www.cnblogs.com/.*").all());
        String title = page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]").get();
        String content = page.getHtml().xpath("//*[@id=\"topics\"]/div/div[2]").get();
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
