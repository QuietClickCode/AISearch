package com.zjj.aisearch.model;

/**
 * @program: AISearch
 * @description: 知乎文章
 * @author: zjj
 * @create: 2019-09-29 15:34:20
 **/
public class ZhihuArticle {

    private String question;
    private String answer;
    private String questionInfo;
    private String createTime;
    private String id;


    @Override
    public String toString() {
        return "ZhihuArticle{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", questionInfo='" + questionInfo + '\'' +
                ", createTime='" + createTime + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(String questionInfo) {
        this.questionInfo = questionInfo;
    }

    public String getCreateTime() {
        return createTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
