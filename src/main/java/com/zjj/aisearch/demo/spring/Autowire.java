package com.zjj.aisearch.demo.spring;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)//作用在字段上面
@Documented
public @interface Autowire {
    String value() default "";
}