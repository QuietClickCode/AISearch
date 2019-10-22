package com.zjj.aisearch.service;

import com.zjj.aisearch.model.QueryForm;
import com.zjj.aisearch.model.SystemLogList;

import java.util.List;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-05 02:51:40
 **/
public interface QueryService {


    public List<SystemLogList> queryForm(QueryForm queryForm);
    public List<String> querySystem();
    public List<String> queryBrowser();
    public List<String> queryDevice();
    public Integer queryCount(QueryForm queryForm);

    Integer queryAiNoteCount(QueryForm queryForm);

    List<SystemLogList> queryAiNote(QueryForm queryForm);
}
