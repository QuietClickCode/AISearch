package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseServiceMock;
import com.aisearch.upms.dao.mapper.UpmsSystemMapper;
import com.aisearch.upms.dao.model.UpmsSystem;
import com.aisearch.upms.dao.model.UpmsSystemExample;

/**
* 降级实现UpmsSystemService接口
* Created by shuzheng on 2019/10/13.
*/
public class UpmsSystemServiceMock extends BaseServiceMock<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

}
