package com.aisearch.upms.common.constant;

import com.aisearch.common.base.BaseResult;

/**
 * @program: AISearch
 * @description: upms系统常量枚举类
 * @author: zjj
 * @create: 2019-10-13 20:24:15
 **/
public class UpmsResult extends BaseResult {

    public UpmsResult(UpmsResultConstant upmsResultConstant, Object data) {
        super(upmsResultConstant.getCode(), upmsResultConstant.getMessage(), data);
    }

}