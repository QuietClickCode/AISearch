package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 红色
 * @author: zjj
 * @create: 2019-11-03 12:02:27
 **/
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("i am a red");
    }
}
