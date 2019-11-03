package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 圆形
 * @author: zjj
 * @create: 2019-11-03 12:00:59
 **/
public class Circle implements Shape {
    @Override
    public void draw() {

        System.out.println("i am a circle");
    }
}
