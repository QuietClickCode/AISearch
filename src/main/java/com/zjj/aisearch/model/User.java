package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: aisearch
 * @description:
 * @author: zjj
 * @create: 2019-09-07 17:37:20
 **/
@Getter
@Setter
@ToString
@Data
public class User {


    private String username;
    private String password;

}
