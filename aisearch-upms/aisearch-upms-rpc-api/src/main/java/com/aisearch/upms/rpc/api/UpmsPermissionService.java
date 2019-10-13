package com.aisearch.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.aisearch.common.base.BaseService;
import com.aisearch.upms.dao.model.UpmsPermission;
import com.aisearch.upms.dao.model.UpmsPermissionExample;

/**
* UpmsPermissionService接口
* Created by shuaisearch on 2017/3/20.
*/
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}