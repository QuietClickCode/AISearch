package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 客户端提交的信息,包含浏览器传过来的所有信息
 * @author: zjj
 * @create: 2019-09-28 00:16:12
 **/
@Getter
@Setter
@Data
@ToString
public class UserInfo {
    private User user;
    private String[] location;
    private String[] browserInfo;
    private String pcOrPhone;
    private String localIp;
}
