package com.zjj.aisearch.mapper;

import com.zjj.aisearch.model.QueryForm;

import java.util.List;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-05 02:52:09
 **/
public interface QueryMapper {

    List<Object> queryForm(QueryForm queryForm);
    List<String> querySystem();
    List<String> queryBrowser();
    List<String> queryDevice();
}
