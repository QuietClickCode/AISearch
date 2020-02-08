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
public class CSDNArticle{

    private Integer id;

    private String title;

    private String content;

    private String createtime;




}
