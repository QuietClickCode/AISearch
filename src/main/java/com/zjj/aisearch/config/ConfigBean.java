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

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir;
    }
}
