package com.aisearch.upms.rpc.service.impl;

import com.aisearch.common.annotation.BaseService;
import com.aisearch.common.base.BaseServiceImpl;
import com.aisearch.upms.dao.mapper.UpmsLogMapper;
import com.aisearch.upms.dao.model.UpmsLog;
import com.aisearch.upms.dao.model.UpmsLogExample;
import com.aisearch.upms.rpc.api.UpmsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsLogService实现
* Created by shuzheng on 2019/10/13.
*/
@Service
@Transactional
@BaseService
public class UpmsLogServiceImpl extends BaseServiceImpl<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsLogServiceImpl.class);

    @Autowired
    UpmsLogMapper upmsLogMapper;

}