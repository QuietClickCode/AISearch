package com.zjj.aisearch.demo.patternDesign.factory;

/**
 * @program: AISearch
 * @description: 圆形
 * @author: zjj
 * @create: 2019-11-03 00:48:41
 **/
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("i am a circle");
    }
}
