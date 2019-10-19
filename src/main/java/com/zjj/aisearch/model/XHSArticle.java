package com.zjj.aisearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: znsh
 * @description: 小红书文章
 * @author: zjj
 * @create: 2019-05-06 01:20:06
 **/
@Document(collection="xhs_article")
public class XHSArticle {

    private String title;

    private String content;

    @Id
    private String id;

    private String createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "XHSArticle{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
