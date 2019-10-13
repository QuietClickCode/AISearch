package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseService;
import com.aisearch.upms.dao.model.UpmsUserRole;
import com.aisearch.upms.dao.model.UpmsUserRoleExample;

/**
* UpmsUserRoleService接口
* Created by shuaisearch on 2017/3/20.
*/
public interface UpmsUserRoleService extends BaseService<UpmsUserRole, UpmsUserRoleExample> {

    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);

}