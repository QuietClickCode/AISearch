package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.QueryForm;
import com.zjj.aisearch.model.SystemLogList;
import com.zjj.aisearch.service.QueryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: AISearch
 * @description: 查询
 * @author: zjj
 * @create: 2019-10-05 02:46:49
 **/
@Controller
@Slf4j
public class QueryController {

    @Autowired
    private QueryService queryServiceImpl;

    @RequestMapping("querySystemLog")
    @ResponseBody
    @ApiOperation("根据条件查询系统操作日志数据")
    public List<SystemLogList> querySystemLog(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryForm(queryForm);
    }

    @RequestMapping("queryCount")
    @ResponseBody
    @ApiOperation("根据条件查询系统操作日志数据总条数")
    public Integer queryCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryCount(queryForm);
        return count;
    }

    @RequestMapping("queryAiNote")
    @ResponseBody
    @ApiOperation("根据条件查询便签记录数据")
    public List<SystemLogList> queryAiNote(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryAiNote(queryForm);
    }

    @RequestMapping("queryAiNoteCount")
    @ResponseBody
    @ApiOperation("根据条件查询便签记录数据总条数")
    public Integer queryAiNoteCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryAiNoteCount(queryForm);
        return count;
    }

    @RequestMapping("queryEditor")
    @ResponseBody
    @ApiOperation("根据条件查询editor记录数据")
    public List<SystemLogList> queryEditor(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryEditor(queryForm);
    }

    @RequestMapping("queryEditorCount")
    @ResponseBody
    @ApiOperation("根据条件查询editor记录数据总条数")
    public Integer queryEditorCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryEditorCount(queryForm);
        return count;
    }

    @RequestMapping("queryList")
    @ResponseBody
    @ApiOperation("根据条件查询搜索记录数据")
    public List<SystemLogList> queryList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryList(queryForm);
    }

    @RequestMapping("queryListCount")
    @ResponseBody
    @ApiOperation("根据条件查询搜索记录数据总条数")
    public Integer queryListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryListCount(queryForm);
        return count;
    }

    @RequestMapping("queryLoginLogList")
    @ResponseBody
    @ApiOperation("根据条件查询登录日志记录数据")
    public List<SystemLogList> queryLoginLogList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryLoginLogList(queryForm);
    }

    @RequestMapping("queryLoginLogListCount")
    @ResponseBody
    @ApiOperation("根据条件查询登录日志记录数据总条数")
    public Integer queryLoginLogListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryLoginLogListCount(queryForm);
        return count;
    }

    @RequestMapping("queryLogoutLogList")
    @ResponseBody
    @ApiOperation("根据条件查询退出日志记录数据")
    public List<SystemLogList> queryLogoutLogList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryLogoutLogList(queryForm);
    }

    @RequestMapping("queryLogoutLogListCount")
    @ResponseBody
    @ApiOperation("根据条件查询退出日志记录数据总条数")
    public Integer queryLogoutLogListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryLogoutLogListCount(queryForm);
        return count;
    }

    @RequestMapping("queryMarkdownList")
    @ResponseBody
    @ApiOperation("根据条件查询Markdown日志记录数据")
    public List<SystemLogList> queryMarkdownList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryMarkdownList(queryForm);
    }

    @RequestMapping("queryMarkdownListCount")
    @ResponseBody
    @ApiOperation("根据条件查询Markdown日志记录数据总条数")
    public Integer queryMarkdownListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryMarkdownListCount(queryForm);
        return count;
    }

    @RequestMapping("queryUserList")
    @ResponseBody
    @ApiOperation("根据条件查询用户记录数据")
    public List<SystemLogList> queryUserList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryUserList(queryForm);
    }

    @RequestMapping("queryUserListCount")
    @ResponseBody
    @ApiOperation("根据条件查询用户记录数据总条数")
    public Integer queryUserListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryUserListCount(queryForm);
        return count;
    }

    @RequestMapping("querySystem")
    @ResponseBody
    @ApiOperation("querySystem")
    public List<String> querySystem() {
        List<String> strings = queryServiceImpl.querySystem();
        return strings;
    }

    @RequestMapping("queryDevice")
    @ResponseBody
    @ApiOperation("queryDevice")
    public List<String> queryDevice() {
        List<String> strings = queryServiceImpl.queryDevice();
        return strings;
    }

    @RequestMapping("queryBrowser")
    @ResponseBody
    @ApiOperation("queryBrowser")
    public List<String> queryBrowser() {
        List<String> strings = queryServiceImpl.queryBrowser();
        return strings;
    }


}
