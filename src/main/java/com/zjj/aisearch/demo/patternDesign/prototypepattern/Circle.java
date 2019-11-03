package com.zjj.aisearch.demo.patternDesign.prototypepattern;

/**
 * @program: AISearch
 * @description: 圆形
 * @author: zjj
 * @create: 2019-11-03 12:32:54
 **/
public class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }
    @Override
    void draw() {
        System.out.println("i am a circle");
    }
}
