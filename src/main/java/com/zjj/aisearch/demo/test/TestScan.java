package com.zjj.aisearch.demo.test;

import com.zjj.aisearch.demo.annotations.ZjjAutowired;
import com.zjj.aisearch.demo.annotations.ZjjController;
import com.zjj.aisearch.demo.annotations.ZjjRequestMapping;
import com.zjj.aisearch.demo.annotations.ZjjService;
import com.zjj.aisearch.demo.controller.MyTestController;
import com.zjj.aisearch.demo.spring.SpringTestController;
import com.zjj.aisearch.demo.spring.TestSpring;
import com.zjj.aisearch.demo.utils.ClazzUtils;
import org.apache.commons.collections.map.HashedMap;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        getPackage2();
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
     * 扫描指定包下的所有类
     */
    private static void getPackage() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        File file = new File("F:\\AISearch\\src\\main\\java\\com\\zjj\\aisearch\\demo\\spring");
        /**
         * 1. String[] list() ；

         说明：返回某个目录下所有文件和目录的文件名，返回类型String[]
         File[] listFiles();
         说明：返回某个目录下所有文件和目录的绝对路径，返回类型File[]
         */
        String[] list = file.list();
        File[] files = file.listFiles();
        List<String> ll = new LinkedList<>();
        String packageName = new String("com.zjj.aisearch.demo.spring.");
        for (String l : list) {
            String[] split = l.split("\\.");
            //System.out.println(split[0]);
            String s = packageName + split[0];
            ll.add(s);
        }
        Map<String, Object> map = new HashMap<>();
        for (String l : ll) {
            //System.out.println(l);
            Class clazz = Class.forName(l);
            try {
                Object o = clazz.newInstance();
                map.put(l, o);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Object o = map.get("com.zjj.aisearch.demo.spring.SpringTestController");
        Object service = map.get("com.zjj.aisearch.demo.spring.SpringTestService");
        Class<?> aClass = o.getClass();
        Field name = aClass.getField("springTestService");
        name.set(o, service);
        Method test = aClass.getMethod("test");
        test.invoke(o);

    }

    /**
     * 扫描指定包下的所有类
     */
    private static void getPackage1() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        File file = new File("F:\\AISearch\\src\\main\\java\\com\\zjj\\aisearch\\demo\\spring");
        /**
         * 1. String[] list() ；

         说明：返回某个目录下所有文件和目录的文件名，返回类型String[]
         File[] listFiles();
         说明：返回某个目录下所有文件和目录的绝对路径，返回类型File[]
         */
        String[] list = file.list();
        File[] files = file.listFiles();
        List<String> ll = new LinkedList<>();
        String packageName = new String("com.zjj.aisearch.demo.spring.");
        for (String l : list) {
            String[] split = l.split("\\.");
            //System.out.println(split[0]);
            String s = packageName + split[0];
            ll.add(s);
        }
        Map<String, Object> map = new HashMap<>();
        Map<Object, Object> map2 = new HashMap<>();
        for (String l : ll) {
            //System.out.println(l);
            Class clazz = Class.forName(l);
            boolean annotationPresent = clazz.isAnnotationPresent(ZjjController.class);
            boolean annotationPresent2 = clazz.isAnnotationPresent(ZjjService.class);
            if (annotationPresent || annotationPresent2) {
                Field[] fields = clazz.getFields();
                for (Field f : fields) {
                    ZjjAutowired declaredAnnotation = f.getDeclaredAnnotation(ZjjAutowired.class);
                    if (declaredAnnotation != null) {

                        System.out.println(f.getType().getSimpleName());
                        Class aClass = Class.forName(packageName + f.getType().getSimpleName());
                        Object o = aClass.newInstance();
                        Object o2 = clazz.newInstance();
                        f.set(o2, o);
                        clazz.getMethod("test").invoke(o2);
                    } else {
                        Object o2 = clazz.newInstance();
                    }
                }
            }

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

    /**
     * 扫描包下的所有类
     */
    public static void getPackage2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //指定包下的所有类的全路径
        List<String> clazzs = ClazzUtils.getClazzName("com.zjj.aisearch.demo.spring", false);
        System.out.println(clazzs);
        //实例化所有加ZjjController的类
        //得到所有类的Class对象
        //容器
        Map<String, Object> map = new HashedMap();
        for (String clazz : clazzs) {
            Class clazzObject = Class.forName(clazz);
            //扫描ZjjService和ZjjController,并实例化
            if (clazzObject.isAnnotationPresent(ZjjService.class) || clazzObject.isAnnotationPresent(ZjjController.class)) {
                Object instance = clazzObject.newInstance();
                //扫描ZjjAutowire
                Field[] fields = clazzObject.getFields();
                //遍历所有字段
                for (Field field : fields) {
                    ZjjAutowired annotation = field.getAnnotation(ZjjAutowired.class);
                    //如果包含ZjjAutowired
                    if (annotation != null) {
                        //获取该字段全名
                        String typeName = field.getType().getTypeName();
                        //实例化该字段
                        Class fieldObject = Class.forName(typeName);
                        Object fieldInstance = fieldObject.newInstance();
                        //注入
                        field.set(instance, fieldInstance);
                        Field[] fields1 = fieldObject.getFields();
                        for (Field field1 : fields1) {
                            ZjjAutowired annotation1 = field1.getAnnotation(ZjjAutowired.class);
                            //如果包含ZjjAutowired
                            if (annotation1 != null) {
                                //获取该字段全名
                                String typeName1 = field1.getType().getTypeName();
                                //实例化该字段
                                Class fieldInstance1 = Class.forName(typeName1);
                                Object fieldInstance2 = fieldInstance1.newInstance();
                                //注入
                                field1.set(fieldInstance, fieldInstance2);
                                map.put(typeName1, fieldInstance);

                            }
                        }
                    }
                }
            }
        }

        TestSpring testSpring = (TestSpring) map.get("com.zjj.aisearch.demo.spring.TestSpring");
        System.out.println(testSpring);
        testSpring.test();
        SpringTestController springTestController = (SpringTestController) map.get("com.zjj.aisearch.demo.spring.SpringTestController");
        System.out.println(springTestController);

    }
}
