package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseServiceMock;
import com.aisearch.upms.dao.mapper.UpmsLogMapper;
import com.aisearch.upms.dao.model.UpmsLog;
import com.aisearch.upms.dao.model.UpmsLogExample;

/**
* 降级实现UpmsLogService接口
* Created by shuzheng on 2019/10/13.
*/
public class UpmsLogServiceMock extends BaseServiceMock<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

}
