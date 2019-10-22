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

    List<SystemLogList> queryEditor(QueryForm queryForm);

    Integer queryEditorCount(QueryForm queryForm);

    Integer queryListCount(QueryForm queryForm);

    List<SystemLogList> queryList(QueryForm queryForm);

    List<SystemLogList> queryLoginLogList(QueryForm queryForm);

    Integer queryLoginLogListCount(QueryForm queryForm);

    List<SystemLogList> queryLogoutLogList(QueryForm queryForm);

    Integer queryLogoutLogListCount(QueryForm queryForm);

    Integer queryMarkdownListCount(QueryForm queryForm);

    List<SystemLogList> queryMarkdownList(QueryForm queryForm);

    Integer queryUserListCount(QueryForm queryForm);

    List<SystemLogList> queryUserList(QueryForm queryForm);
}
