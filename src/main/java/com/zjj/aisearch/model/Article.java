package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: csdn文章
 * @author: zjj
 * @create: 2019-09-21 19:21:35
 **/

@Getter
@Setter
@Data
@ToString
public class Article {

    private Integer id;//主键Id

    private String title;//标题

    private String content;//内容

    private String createtime;//创建时间


}
