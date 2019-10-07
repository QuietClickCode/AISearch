package com.zjj.aisearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "zhihu",type = "article", shards = 1,replicas = 0, refreshInterval = "-1")
public class ZhiHuArticle {
    @Id
    private Integer id;
    @Field
    private String question;
    @Field
    private String question_info;
    @Field
    private String answer;
    @Field
    private String createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_info() {
        return question_info;
    }

    public void setQuestion_info(String question_info) {
        this.question_info = question_info;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}