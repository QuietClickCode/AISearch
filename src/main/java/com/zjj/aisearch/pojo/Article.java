package com.zjj.aisearch.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author sanglp
 * @create 2018-07-04 9:06
 * @desc 文章实体类,默认情况下添加@Document注解会对实体中的所有属性建立索引，
 **/
@Document(indexName = "projectname",type = "article",indexStoreType = "fs",shards = 5,replicas = 1,refreshInterval = "-1")
public class Article implements Serializable {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String abstracts;
    /**
     * 内容
     */
    private String content;
    /**
     * 发表时间
     */
    private String postTime;
    /**
     * 点击率
     */
    private String clickCount;
    /**
     * 作者
     */
    private Author author;
    /**
     * 所属教程
     */
    private Tutorial tutorial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }
}