package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 工厂生成器
 * @author: zjj
 * @create: 2019-11-03 12:13:22
 **/
public class FactoryProducer  {
    public static AbstractFactory getFactory(String type) {
        if (type.equalsIgnoreCase("shape")) {
            return new ShapeFactory();
        } else if (type.equalsIgnoreCase("color")) {
            return new ColorFactory();
        }
        return null;
    }
}
