package com.zjj.aisearch.model;

/**
 * @program: AISearch
 * @description: 搜索记录
 * @author: zjj
 * @create: 2019-09-07 23:19:11
 **/
public class SearchRecord {

    private Integer id;

    private String keyword;

    private String searchTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }


    @Override
    public String toString() {
        return "SearchRecord{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                ", searchTime='" + searchTime + '\'' +
                '}';
    }
}
