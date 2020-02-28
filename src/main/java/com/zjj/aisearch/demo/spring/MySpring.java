package com.zjj.aisearch.demo.spring;

import com.zjj.aisearch.demo.annotations.ZjjRequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: AISearch
 * @description: 自己写的Spring
 * @author: zjj
 * @create: 2020-02-28 11:54:23
 **/
public class MySpring {
    public static void main(String[] args) throws Exception, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1:扫描包下的所有类
        Class controller = Class.forName("com.zjj.aisearch.demo.spring.SpringTestController");
        SpringTestController springTestController = (SpringTestController) controller.newInstance();
        Class service = Class.forName("com.zjj.aisearch.demo.spring.SpringTestService");
        SpringTestService springTestService = (SpringTestService) service.newInstance();
        Method test = controller.getMethod("test");
        Field springTestService1 = controller.getField("name");
        springTestService1.set(springTestController,"xixi");
        System.out.println(springTestService1.get(springTestController));
        Field springTestService2 = controller.getField("springTestService");
        springTestService2.set(springTestController,springTestService);
        springTestController.test();
        ZjjRequestMapping declaredAnnotation = test.getDeclaredAnnotation(ZjjRequestMapping.class);
        //2.获取所有类上的注解
        //3.判断注解是否是ZjjController
        //4.是的话实例化,放到容器中交给容器管理
        //5.扫描字段上的注解
        //6.判断是否是ZjjAutowired
        //7.是的话,通过反射注入实例
        //8.测试直接使用
    }
}
