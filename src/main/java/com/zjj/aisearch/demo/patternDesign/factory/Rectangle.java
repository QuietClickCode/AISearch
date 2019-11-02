package com.zjj.aisearch.demo.patternDesign.factory;

/**
 * @program: AISearch
 * @description: 三角形
 * @author: zjj
 * @create: 2019-11-03 00:47:37
 **/
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("i am a rectangle");
    }
}
