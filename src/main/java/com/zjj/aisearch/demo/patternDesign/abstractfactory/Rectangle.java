package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: Rectangle
 * @author: zjj
 * @create: 2019-11-03 11:59:07
 **/
public class Rectangle implements Shape{


    @Override
    public void draw() {
        System.out.println("i am a rectangle");
    }
}
