package com.zjj.aisearch.javaBasic;

/**
 * @program: AISearch
 * @description: filter过滤器
 * @author: zjj
 * @create: 2019-12-21 19:14:34
 **/
public class Filter {
    /**
     * 可以认为是servlet的加强版,对用户请求进行预处理
     * 也可对响应进行后处理
     * 请求到达servlet之前,拦截请求
     * 修改头和数据
     * 响应到达客户端前,拦截,修改响应头和数据
     */


    /**
     * 拦截器是AOP中用于在方法访问之前,进行拦截,之前之后加入一些操作,
     * 区别:
     * filter基于函数回调,Interceptor基于反射
     * fi依赖servlet容器,In不依赖
     * Filter对所有请求起作用,Interceptor自拦截Action请求
     * Interceptor可以多次调用,filter容器初始化调用一次
     *
     * 过滤器可修改请求头,拦截器不能
     */
}
