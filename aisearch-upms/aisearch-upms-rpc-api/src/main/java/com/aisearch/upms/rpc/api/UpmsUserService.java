package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseService;
import com.aisearch.upms.dao.model.UpmsUser;
import com.aisearch.upms.dao.model.UpmsUserExample;

/**
* UpmsUserService接口
* Created by shuaisearch on 2017/3/20.
*/
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);

}