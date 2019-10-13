package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseService;
import com.aisearch.upms.dao.model.UpmsUserOrganization;
import com.aisearch.upms.dao.model.UpmsUserOrganizationExample;

/**
* UpmsUserOrganizationService接口
* Created by shuaisearch on 2017/3/20.
*/
public interface UpmsUserOrganizationService extends BaseService<UpmsUserOrganization, UpmsUserOrganizationExample> {

    /**
     * 用户组织
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, int id);

}