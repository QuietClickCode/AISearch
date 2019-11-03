package com.zjj.aisearch.demo.patternDesign.prototypepattern;

/**
 * @program: AISearch
 * @description: 方形
 * @author: zjj
 * @create: 2019-11-03 12:31:53
 **/
public class Square extends Shape {
    @Override
    void draw() {

        System.out.println("i am a square");
    }

    public Square() {
        type = "square";
    }
}
