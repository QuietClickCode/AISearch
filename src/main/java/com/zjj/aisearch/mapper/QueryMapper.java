package com.zjj.aisearch.mapper;

import com.zjj.aisearch.model.EditorList;
import com.zjj.aisearch.model.QueryForm;
import com.zjj.aisearch.model.SystemLogList;

import java.util.List;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-05 02:52:09
 **/
public interface QueryMapper {

    List<SystemLogList> queryForm(QueryForm queryForm);
    List<String> querySystem();
    List<String> queryBrowser();
    List<String> queryDevice();
    Integer queryCount(QueryForm queryForm);
    Integer queryUserTotalCount();

    Integer queryAiNoteCount(QueryForm queryForm);

    List<SystemLogList> queryAiNote(QueryForm queryForm);

    List<EditorList> queryEditor(QueryForm queryForm);

    Integer queryEditorCount(QueryForm queryForm);

    List<SystemLogList> queryList(QueryForm queryForm);

    Integer queryListCount(QueryForm queryForm);

    Integer queryLoginLogListCount(QueryForm queryForm);

    List<SystemLogList> queryLoginLogList(QueryForm queryForm);

    List<SystemLogList> queryLogoutLogList(QueryForm queryForm);

    Integer queryLogoutLogListCount(QueryForm queryForm);

    List<SystemLogList> queryMarkdownList(QueryForm queryForm);

    Integer queryMarkdownListCount(QueryForm queryForm);

    List<SystemLogList> queryUserList(QueryForm queryForm);

    Integer queryUserListCount(QueryForm queryForm);
}
