package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 抽象工厂
 * @author: zjj
 * @create: 2019-11-03 12:05:03
 **/
public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
