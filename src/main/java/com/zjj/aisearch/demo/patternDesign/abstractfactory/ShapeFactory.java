package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 形状工厂
 * @author: zjj
 * @create: 2019-11-03 12:06:42
 **/
public class ShapeFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        if (shape == null) {
            return null;
        }
        if (shape.equalsIgnoreCase("circle")) {
            return new Circle();
        }
        else if (shape.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        }
        else if (shape.equalsIgnoreCase("square")) {
            return new Square();
        }
        return null;
    }
    @Override
    public Color getColor(String color) {
        return null;
    }
}
