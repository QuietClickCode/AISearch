package com.aisearch.upms.server;

import com.aisearch.common.base.BaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-13 21:19:03
 **/
public class Initialize implements BaseInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(Initialize.class);

    @Override
    public void init() {
        LOGGER.info(">>>>> 系统初始化");
    }
}
