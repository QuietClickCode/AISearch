package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseServiceMock;
import com.aisearch.upms.dao.mapper.UpmsUserMapper;
import com.aisearch.upms.dao.model.UpmsUser;
import com.aisearch.upms.dao.model.UpmsUserExample;

/**
* 降级实现UpmsUserService接口
* Created by shuaisearch on 2017/3/20.
*/
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        return null;
    }

}
