package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 查询
 * @author: zjj
 * @create: 2019-10-05 02:49:44
 **/
@Data
@Getter
@Setter
@ToString
public class QueryForm {

    private String keyword;
    private Integer startpage;
    private String location;
    private String system;
    private String device;
    private String browser;
    private String[] createtime;

}
