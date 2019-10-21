package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class MarkDown {

    private Integer id;

    private String title;

    private String content;

    private String createtime;
    private Integer loginLogId;


}
