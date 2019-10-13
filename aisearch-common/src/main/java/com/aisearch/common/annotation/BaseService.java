package com.aisearch.common.annotation;

import java.lang.annotation.*;

/**
 * @program: AISearch
 * @description: 初始化继承BaseService的service
 * @author: zjj
 * @create: 2019-10-13 18:35:44
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
