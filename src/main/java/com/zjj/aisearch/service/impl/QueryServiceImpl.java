package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.QueryMapper;
import com.zjj.aisearch.model.QueryForm;
import com.zjj.aisearch.model.SystemLogList;
import com.zjj.aisearch.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-05 02:52:40
 **/
@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    private QueryMapper queryMapper;


    @Override
    public List<SystemLogList> queryForm(QueryForm queryForm) {
        return queryMapper.queryForm(queryForm);
    }

    @Override
    public List<String> querySystem() {
        return queryMapper.querySystem();
    }

    @Override
    public List<String> queryBrowser() {
        return queryMapper.queryBrowser();
    }

    @Override
    public List<String> queryDevice() {
        return queryMapper.queryDevice();
    }

    @Override
    public Integer queryCount(QueryForm queryForm) {
        return queryMapper.queryCount(queryForm);
    }

    @Override
    public Integer queryAiNoteCount(QueryForm queryForm) {
        return queryMapper.queryAiNoteCount(queryForm);
    }

    @Override
    public List<SystemLogList> queryAiNote(QueryForm queryForm) {
        return queryMapper.queryAiNote(queryForm);
    }

    @Override
    public List<SystemLogList> queryEditor(QueryForm queryForm) {
        return queryMapper.queryEditor(queryForm);
    }

    @Override
    public Integer queryEditorCount(QueryForm queryForm) {
        return queryMapper.queryEditorCount(queryForm);
    }

    @Override
    public Integer queryListCount(QueryForm queryForm) {
        return queryMapper.queryListCount(queryForm);
    }

    @Override
    public List<SystemLogList> queryList(QueryForm queryForm) {
        return queryMapper.queryList(queryForm);
    }
}
