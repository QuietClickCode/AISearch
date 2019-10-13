package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseServiceMock;
import com.aisearch.upms.dao.mapper.UpmsOrganizationMapper;
import com.aisearch.upms.dao.model.UpmsOrganization;
import com.aisearch.upms.dao.model.UpmsOrganizationExample;

/**
* 降级实现UpmsOrganizationService接口
* Created by shuzheng on 2019/10/13.
*/
public class UpmsOrganizationServiceMock extends BaseServiceMock<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

}
