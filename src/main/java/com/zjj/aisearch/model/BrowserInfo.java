package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 用户浏览器信息
 * @author: zjj
 * @create: 2019-09-27 10:24:59
 **/
@Data
@Getter
@Setter
@ToString
public class BrowserInfo {
    private String browserInfoId;
    private String system;
    private String browserType;
    private String browserVersion;
}
