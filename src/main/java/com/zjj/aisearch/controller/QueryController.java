package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.QueryForm;
import com.zjj.aisearch.model.SystemLogList;
import com.zjj.aisearch.service.QueryService;
import io.swagger.annotations.Api;
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
@Api(value = "测试Controller" , tags = "测试类")
public class QueryController {

    @Autowired
    private QueryService queryServiceImpl;

    @ApiOperation(value = "测试方法")
    @RequestMapping("querySystemLog")
    @ResponseBody
    public List<SystemLogList> querySystemLog(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryForm(queryForm);
    }

    @ApiOperation(value = "测试返回方法",notes = "必传参数: name")
    @RequestMapping("querySystem")
    @ResponseBody
    public List<String> querySystem() {
        List<String> strings = queryServiceImpl.querySystem();
        return strings;
    }
    @RequestMapping("queryDevice")
    @ResponseBody
    public List<String> queryDevice() {
        List<String> strings = queryServiceImpl.queryDevice();
        return strings;
    }
    @RequestMapping("queryBrowser")
    @ResponseBody
    public List<String> queryBrowser() {
        List<String> strings = queryServiceImpl.queryBrowser();
        return strings;
    }
}
