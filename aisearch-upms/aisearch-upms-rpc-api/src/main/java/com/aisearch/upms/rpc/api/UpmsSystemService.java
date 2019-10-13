package com.aisearch.upms.rpc.api;

import com.aisearch.common.base.BaseService;
import com.aisearch.upms.dao.model.UpmsSystem;
import com.aisearch.upms.dao.model.UpmsSystemExample;

/**
* UpmsSystemService接口
* Created by shuaisearch on 2017/3/20.
*/
public interface UpmsSystemService extends BaseService<UpmsSystem, UpmsSystemExample> {

    /**
     * 根据name获取UpmsSystem
     * @param name
     * @return
     */
    UpmsSystem selectUpmsSystemByName(String name);

}