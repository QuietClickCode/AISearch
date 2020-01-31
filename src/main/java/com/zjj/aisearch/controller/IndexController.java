package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.ZhihuArticle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: aisearch
 * @description: 知乎爬虫
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


    @RequestMapping("zhihu")
    @ResponseBody
    public void zhihu(String keyword) {
        Spider.create(this).addUrl("https://www.zhihu.com/question/266090769/answer/722471012").thread(1).run();
    }


    @Override
    public void process(Page page) {
        //简书
       /* page.addTargetRequests(page.getHtml().links().regex("https://www.jianshu.com/p/[a-z 0-9]{12}").all());
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
        }*/
      //知乎
        page.addTargetRequests(page.getHtml().links().regex("https://www.zhihu.com/.*").all());
        page.addTargetRequests(page.getHtml().links().regex("https://www.zhihu.com/question/[0-9]{8,9}").all());
        page.addTargetRequests(page.getHtml().links().regex("https://www.zhihu.com/answer/[0-9]{8,9}").all());
        page.addTargetRequests(page.getHtml().links().regex("https://www.zhihu.com/question/[0-9]{8,9}/answer/[0-9]{8,9}").all());
        page.addTargetRequests(page.getHtml().links().regex("https://zhuanlan.zhihu.com/p/[0-9]{8,9}").all());
        String question = page.getHtml().get();
        String questionInfo = page.getHtml().get();
        String answer = page.getHtml().get();
        System.out.println(question + "zzzzzzzzzzzzzzzzzzzzzz");
        System.out.println(questionInfo + "yyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        System.out.println(answer+"xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        if (question != null && questionInfo != null && answer != null) {
            System.out.println("=====================");
            page.putField("answer", HTMLUtil.delHTMLTag(answer));
            page.putField("question", HTMLUtil.delHTMLTag(question));
            page.putField("questionInfo", HTMLUtil.delHTMLTag(questionInfo));
            /*page.putField("answer",answer );
            page.putField("question", question);
            page.putField("questionInfo", questionInfo);*/
            ZhihuArticle article = new ZhihuArticle();
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(page.getResultItems().get("answer") == null ? "" : page.getResultItems().get("answer").toString());
            article.setAnswer(m.replaceAll(""));
            article.setCreateTime(DateConvert.getTime());
            this.indexService.insertZhihuArticle(article);
            System.out.println(article.getId());
        }

        System.out.println("-----------------------");


    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(1000).setRetryTimes(1);
    }
}
