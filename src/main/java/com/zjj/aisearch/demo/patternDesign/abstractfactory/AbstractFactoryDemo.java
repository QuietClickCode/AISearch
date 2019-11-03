package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 抽象工厂模式
 * @author: zjj
 * @create: 2019-11-03 11:56:49
 **/
public class AbstractFactoryDemo  {
    /**
     * 抽象工厂模式是围绕一个超级工厂创建其他工厂,工厂的工厂
     */

    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");
        AbstractFactory colorFactory = FactoryProducer.getFactory("color");
        Shape circle = shapeFactory.getShape("circle");
        Shape square = shapeFactory.getShape("square");
        Shape rectangle = shapeFactory.getShape("rectangle");
        Color red = colorFactory.getColor("red");
        Color green = colorFactory.getColor("green");
        Color blue = colorFactory.getColor("blue");
        circle.draw();
        square.draw();
        rectangle.draw();
        red.fill();
        blue.fill();
        green.fill();
    }
}
