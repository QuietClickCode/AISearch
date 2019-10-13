package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 岗位
 * @author: zjj
 * @create: 2019-09-30 21:26:21
 **/
@Getter
@Setter
@Data
@ToString
public class Position {
    private Integer positionId;
    private String positionName;
    private Integer deptId;
}
