package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseServiceMock;
import com.aisearch.upms.dao.mapper.UpmsUserPermissionMapper;
import com.aisearch.upms.dao.model.UpmsUserPermission;
import com.aisearch.upms.dao.model.UpmsUserPermissionExample;

/**
* 降级实现UpmsUserPermissionService接口
* Created by shuzheng on 2019/10/13.
*/
public class UpmsUserPermissionServiceMock extends BaseServiceMock<UpmsUserPermissionMapper, UpmsUserPermission, UpmsUserPermissionExample> implements UpmsUserPermissionService {

}
