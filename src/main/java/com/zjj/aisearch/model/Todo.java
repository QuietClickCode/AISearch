package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @program: AISearch
 * @description:待办事项
 * @author: zjj
 * @create: 2019年12月4日20:40:40
 **/
@Getter
@Setter
@Data
@ToString
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;//主键id

    private String content;//内容

    private String createtime;//创建时间

}
