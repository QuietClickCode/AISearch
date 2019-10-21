package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 搜索记录
 * @author: zjj
 * @create: 2019-09-07 23:19:11
 **/
@Data
@Getter
@Setter
@ToString
public class SearchRecord {

    private Integer id;

    private String keyword;

    private String searchTime;

    private Integer loginLogId;





}
