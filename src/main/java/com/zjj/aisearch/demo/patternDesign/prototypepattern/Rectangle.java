package com.zjj.aisearch.demo.patternDesign.prototypepattern;

/**
 * @program: AISearch
 * @description: Rectangle
 * @author: zjj
 * @create: 2019-11-03 12:29:44
 **/
public class Rectangle extends Shape {

    public Rectangle() {
        type = "rectangle";
    }
    @Override
    void draw() {
        System.out.println("i am a rectangle");
    }
}
