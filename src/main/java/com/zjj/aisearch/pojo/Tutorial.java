package com.zjj.aisearch.pojo;

import java.io.Serializable;

/**
 * @author sanglp
 * @create 2018-07-04 9:03
 * @desc 实体类
 **/
public class Tutorial implements Serializable {
    private Long id;
    //教程名称
    private String name ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}