package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-21 19:21:35
 **/

@Getter
@Setter
@Data
@ToString
public class ZhiHuArticle {

    private Integer id;

    private String answer;

    private String quertion;
    private String quertionInfo;

    private String createtime;




}
