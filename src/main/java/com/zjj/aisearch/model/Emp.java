package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @program: AISearch
 * @description: 员工
 * @author: zjj
 * @create: 2019-09-30 21:26:21
 **/
@Getter
@Setter
@Data
@ToString
public class Emp {
    private Integer empId;
    private String empNo;
    private String empName;
    private String empSex;
    private Date empDate;
    private Integer empDeptId;
    private Integer empPostionId;
    private String empAddress;
}
