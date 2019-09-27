package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 用户位置ip信息
 * @author: zjj
 * @create: 2019-09-27 09:47:03
 **/
@Data
@Getter
@Setter
@ToString
public class Location {
    private String locationId;
    private String ip;
    private String location;
    private String X;
    private String Y;
    private String keyword;
    private String localIp;
}
