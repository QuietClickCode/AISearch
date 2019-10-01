package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 结合
 * @author: zjj
 * @create: 2019-09-27 13:43:21
 **/
@Data
@Getter
@Setter
@ToString
public class UserLocation extends User{
    private Location location;
    private BrowserInfo browserInfo;
}
