package com.aisearch.upms.server.controller.manage;

import com.aisearch.common.base.BaseController;
import com.aisearch.upms.rpc.api.UpmsOrganizationService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UpmsOrganizationcontroller
 * Created by shuzheng on 2019/10/13.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "UpmsOrganization控制器", description = "UpmsOrganization管理")
public class UpmsOrganizationController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsOrganizationController.class);

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

}