package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 系统操作日志
 * @author: zjj
 * @create: 2019-10-02 00:09:12
 **/
@Data
@Getter
@Setter
@ToString
public class SystemLog {

    private Integer id;//主键id
    private String createtime;//创建时间
    private String operation;//执行的操作
    private Integer loginLogId;//登录Id

}
