package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-21 21:13:58
 **/

@Data
@Getter
@Setter
@ToString
public class Item {

    private Integer id;
    private String title;
    private String sellPoint;
    private Float price;


}
