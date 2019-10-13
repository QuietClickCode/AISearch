package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseServiceMock;
import com.aisearch.upms.dao.mapper.UpmsUserOrganizationMapper;
import com.aisearch.upms.dao.model.UpmsUserOrganization;
import com.aisearch.upms.dao.model.UpmsUserOrganizationExample;

/**
* 降级实现UpmsUserOrganizationService接口
* Created by shuzheng on 2019/10/13.
*/
public class UpmsUserOrganizationServiceMock extends BaseServiceMock<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

}
