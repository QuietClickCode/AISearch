package com.zjj.aisearch.demo.spring;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-03-14 16:08:35
 **/
@Controller
public class MyController {
    @Autowire("myservice")
    private MyService service;

    public void test() {
        service.say("hello,spring IoC");
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationApplicationContext("applicationContext.properties");
        MyController controller = context.getBean("com.zjj.aisearch.demo.spring.MyController", MyController.class);
        controller.test();
    }
}
