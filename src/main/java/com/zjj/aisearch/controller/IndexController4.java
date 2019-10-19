package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.XHSArticle;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateConvert;
import com.zjj.aisearch.utils.HTMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.UUID;

/**
 * @program: aisearch
 * @description: 入口
 * @author: zjj
 * @create: 2019-09-07 16:53:15
 **/
@Controller
public class IndexController4 implements PageProcessor {

    @Autowired
    private IndexService indexService;

    @Autowired
    MongoTemplate mongoTemplate;


    @RequestMapping("db")
    @ResponseBody
    public void jianshu(String keyword) {
        Spider.create(this).addUrl("https://www.xiaohongshu.com/discovery/item/5d38482200000000260101b0").thread(3).run();
    }



    @Override
    public void process(Page page) {
        //db
        page.addTargetRequests(page.getHtml().links().regex("https://www.xiaohongshu.com/discovery/item/.*").all());
        String title = "haha";
        String content = page.getHtml().xpath("/html/body").get();
        System.out.println(title+"--------------");
        System.out.println(content+"--------------");
        if (title != null && content != null && title != "" && content != "") {
            page.putField("title", HTMLUtil.delHTMLTag(title));
            page.putField("content",HTMLUtil.delHTMLTag(content));
            XHSArticle article = new XHSArticle();
            article.setId(UUID.randomUUID().toString());
            article.setContent(page.getResultItems().get("content") == null ? "" : page.getResultItems().get("content").toString());
            article.setTitle(page.getResultItems().get("title") == null ? "" : page.getResultItems().get("title").toString());
            article.setCreateTime(DateConvert.getTime());
            mongoTemplate.save(article);
            System.out.println(article.getId());
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(5000).setRetryTimes(3);
    }
}
