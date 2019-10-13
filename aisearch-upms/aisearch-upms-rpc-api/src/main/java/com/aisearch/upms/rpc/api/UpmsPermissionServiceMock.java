package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseServiceMock;
import com.aisearch.upms.dao.mapper.UpmsPermissionMapper;
import com.aisearch.upms.dao.model.UpmsPermission;
import com.aisearch.upms.dao.model.UpmsPermissionExample;

/**
* 降级实现UpmsPermissionService接口
* Created by shuzheng on 2019/10/13.
*/
public class UpmsPermissionServiceMock extends BaseServiceMock<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements UpmsPermissionService {

}
