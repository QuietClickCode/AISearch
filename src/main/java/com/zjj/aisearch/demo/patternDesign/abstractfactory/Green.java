package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 绿色
 * @author: zjj
 * @create: 2019-11-03 12:03:37
 **/
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("i am a green");
    }
}
