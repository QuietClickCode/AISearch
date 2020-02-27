package com.zjj.aisearch.demo.test;

import com.zjj.aisearch.demo.annotations.ZjjRequestMapping;
import com.zjj.aisearch.demo.controller.MyTestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: AISearch
 * @description: 扫描注解测试
 * @author: zjj
 * @create: 2020-02-27 23:52:45
 **/
public class TestScan {
    public static void main(String[] args) throws Exception, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {

        //getClassAnnotation();
        //getMethodAnnotation(requestURL);

    }

    /**
     * get指定类的所有注解
     */
    private static void getClassAnnotation() {
        Class newClass = MyTestController.class;
        for (Annotation annotation : newClass.getDeclaredAnnotations()) {
            System.out.println(annotation.toString());
        }
    }

    /**
     * get指定类的所有自己定义的方法上的注解
     * 获取指定注解
     *
     * @param requestURL
     */
    public static Object getMethodAnnotation(StringBuffer requestURL) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        Class newClass = MyTestController.class;
        Class aClass = Class.forName("com.zjj.aisearch.demo.controller.MyTestController");
        Constructor constructor = aClass.getConstructor();
        Object o = constructor.newInstance();
     /*   Method test = aClass.getMethod("test2",String.class);
        Object invoke = test.invoke(o,"lili");
        System.out.println(invoke);*/

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            ZjjRequestMapping declaredAnnotation = method.getDeclaredAnnotation(ZjjRequestMapping.class);
            if (declaredAnnotation != null) {
                System.out.println(method);
                String value = declaredAnnotation.value();
                System.out.println("http://localhost" + value);
                System.out.println(requestURL);
                System.out.println(("http://localhost" + value).equals(requestURL));
                if (("http://localhost" + value).equals(requestURL.toString())) {
                    Object invoke = method.invoke(o);
                    return invoke;
                }
            }
        }
        return "--------";
    }
}
