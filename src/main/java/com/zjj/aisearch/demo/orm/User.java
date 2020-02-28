package com.zjj.aisearch.demo.orm;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户表
 *
 * @author hejiaxuan
 */
@Table(name = "user")
public class User {

    /**
     * 用户名
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户id
     */
    @Id
    @Column(name = "id")
    private int id;

    /**
     * 年龄
     */
    @Column(name = "age")
    private int age;

    /**
     * mark
     */
    @Column(name = "mark")
    private String mark;

    /**
     * create_date
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * status
     */
    @Column(name = "status")
    private int status;

	//getter and setter and toString
}