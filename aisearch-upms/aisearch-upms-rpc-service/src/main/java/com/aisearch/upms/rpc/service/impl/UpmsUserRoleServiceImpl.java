package com.aisearch.upms.rpc.service.impl;

import com.aisearch.common.annotation.BaseService;
import com.aisearch.common.base.BaseServiceImpl;
import com.aisearch.upms.dao.mapper.UpmsUserRoleMapper;
import com.aisearch.upms.dao.model.UpmsUserRole;
import com.aisearch.upms.dao.model.UpmsUserRoleExample;
import com.aisearch.upms.rpc.api.UpmsUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsUserRoleService实现
* Created by shuzheng on 2019/10/13.
*/
@Service
@Transactional
@BaseService
public class UpmsUserRoleServiceImpl extends BaseServiceImpl<UpmsUserRoleMapper, UpmsUserRole, UpmsUserRoleExample> implements UpmsUserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserRoleServiceImpl.class);

    @Autowired
    UpmsUserRoleMapper upmsUserRoleMapper;

}