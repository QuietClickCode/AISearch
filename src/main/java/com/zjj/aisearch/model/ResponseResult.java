package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @program: AISearch
 * @description: 响应对象
 * @author: zjj
 * @create: 2019-09-21 19:21:35
 **/

@Getter
@Setter
@Data
@ToString
@Accessors(chain = true)
public class ResponseResult {

    private String msg;//消息

    private Object data;//数据

    private String url;//跳转url

    private Integer status;//状态,成功0,失败-1


}
