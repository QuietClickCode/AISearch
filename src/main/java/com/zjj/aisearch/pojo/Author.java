package com.zjj.aisearch.pojo;

import java.io.Serializable;

/**
 * @author sanglp
 * @create 2018-07-04 9:04
 * @desc 作者实体类
 **/
public class Author  implements Serializable{
    /**
     * 作者ID
     */
    private Long id;
    /**
     * 作者姓名
     */
    private String name;
    /**
     * 作者简介
     */
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}