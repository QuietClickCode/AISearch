package com.aisearch.upms.rpc.service.impl;

import com.aisearch.common.annotation.BaseService;
import com.aisearch.common.base.BaseServiceImpl;
import com.aisearch.upms.dao.mapper.UpmsUserMapper;
import com.aisearch.upms.dao.model.UpmsUser;
import com.aisearch.upms.dao.model.UpmsUserExample;
import com.aisearch.upms.rpc.api.UpmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsUserService实现
* Created by shuzheng on 2019/10/13.
*/
@Service
@Transactional
@BaseService
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;

}