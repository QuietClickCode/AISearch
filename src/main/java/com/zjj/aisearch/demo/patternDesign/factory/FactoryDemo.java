package com.zjj.aisearch.demo.patternDesign.factory;

/**
 * @program: AISearch
 * @description: 工厂模式
 * @author: zjj
 * @create: 2019-11-03 00:40:12
 **/
public class FactoryDemo {

    /**
     * 可以这样理解工厂模式:创建对象和 使用对象是分开的
     */

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape circle = shapeFactory.getShape("circle");
        circle.draw();

        Shape square = shapeFactory.getShape("square");
        square.draw();

        Shape rectangle = shapeFactory.getShape("rectangle");
        rectangle.draw();


    }
}
