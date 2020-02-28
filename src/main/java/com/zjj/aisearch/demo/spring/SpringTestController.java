package com.zjj.aisearch.demo.spring;

import com.zjj.aisearch.demo.annotations.ZjjAutowired;
import com.zjj.aisearch.demo.annotations.ZjjController;
import com.zjj.aisearch.demo.annotations.ZjjRequestMapping;
import com.zjj.aisearch.demo.tomcat.MyRequest;
import com.zjj.aisearch.demo.tomcat.MyResponse;
import com.zjj.aisearch.demo.tomcat.MyServlet;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @program: AISearch
 * @description: 自己写spring框架测试Controller
 * @author: zjj
 * @create: 2020-02-28 19:14:57
 **/
@ZjjController
public class SpringTestController extends MyServlet {

    @ZjjAutowired
    public SpringTestService springTestService;

    public String name = "haha";

    @ZjjRequestMapping("/testspring")
    public void test() {
        springTestService.test();
    }

    public void test2() {
        System.out.println("test2----");
    }

    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            if (myRequest.getUrl().equals("/testspring")) {
                Class controller = Class.forName("com.zjj.aisearch.demo.spring.SpringTestController");
                Class service = Class.forName("com.zjj.aisearch.demo.spring.SpringTestService");
                SpringTestService springTestService = (SpringTestService) service.newInstance();
                SpringTestController springTestController = (SpringTestController) controller.newInstance();
                Field springTestService2 = controller.getField("springTestService");
                System.out.println(springTestService2.get(springTestController));
                System.out.println(springTestService);
                this.springTestService = springTestService;
                System.out.println(springTestService2.get(springTestController));
                test();
                myResponse.write("Hello, this is my blog");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
