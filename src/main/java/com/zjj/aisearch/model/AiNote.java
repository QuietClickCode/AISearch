package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 记录所思所想的便签
 * @author: zjj
 * @create: 2019-09-30 21:26:21
 **/
@Getter
@Setter
@Data
@ToString
public class AiNote {

    private Integer id;//主键id

    private String content;//内容
    private String title;//内容

    private String createtime;//创建时间

    private Integer loginLogId;//登录日志id

}
