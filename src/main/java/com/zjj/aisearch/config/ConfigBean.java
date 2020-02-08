package com.zjj.aisearch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: AISearch
 * @description: 配置实体
 * @author: zjj
 * @create: 2019-12-15 11:55:35
 **/
@Component
@ConfigurationProperties(prefix = "config")
@PropertySource(value = "classpath:/config.properties")
public class ConfigBean {
    private String imgDir;
    private String REDIS_ITEM_PRE;
    private Long ITEM_CACHE_EXPIRE;

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir;
    }

    public String getREDIS_ITEM_PRE() {
        return REDIS_ITEM_PRE;
    }

    public void setREDIS_ITEM_PRE(String REDIS_ITEM_PRE) {
        this.REDIS_ITEM_PRE = REDIS_ITEM_PRE;
    }

    public Long getITEM_CACHE_EXPIRE() {
        return ITEM_CACHE_EXPIRE;
    }

    public void setITEM_CACHE_EXPIRE(Long ITEM_CACHE_EXPIRE) {
        this.ITEM_CACHE_EXPIRE = ITEM_CACHE_EXPIRE;
    }
}
