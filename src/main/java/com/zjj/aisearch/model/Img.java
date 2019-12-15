package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 图片
 * @author: zjj
 * @create: 2019-12-15 12:22:31
 **/
@Data
@Getter
@Setter
@ToString
public class Img {

    private Integer id;
    private String imgOriginName;
    private String imgNewName;
    private Long size;
    private String type;
    private String location;
    private String createtime;
}
