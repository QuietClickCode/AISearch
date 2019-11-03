package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 方形
 * @author: zjj
 * @create: 2019-11-03 12:00:24
 **/
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("i am a square");
    }
}
