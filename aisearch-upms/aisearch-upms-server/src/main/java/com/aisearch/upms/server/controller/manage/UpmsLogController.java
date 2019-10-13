package com.aisearch.upms.server.controller.manage;

import com.aisearch.common.base.BaseController;
import com.aisearch.upms.rpc.api.UpmsLogService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UpmsLogcontroller
 * Created by shuzheng on 2019/10/13.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "UpmsLog控制器", description = "UpmsLog管理")
public class UpmsLogController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsLogController.class);

    @Autowired
    private UpmsLogService upmsLogService;

}