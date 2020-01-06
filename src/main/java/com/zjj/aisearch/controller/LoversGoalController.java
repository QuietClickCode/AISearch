package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.QueryForm;
import com.zjj.aisearch.model.SystemLogList;
import com.zjj.aisearch.service.LoversGoalService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: AISearch
 * @description: LoversGoal
 * @author: zjj
 * @create: 2019-10-05 02:46:49
 **/
@RestController
@Slf4j
public class LoversGoalController {

    @Autowired
    private LoversGoalService loversGoalServiceImpl;

    @PostMapping("queryTaskList")
    @ApiOperation("查询任务列表")
    public List<SystemLogList> querySystemLog(@RequestBody QueryForm queryForm) {
        return null;
    }

}
