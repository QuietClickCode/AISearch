package com.zjj.aisearch.demo.patternDesign.abstractfactory;

/**
 * @program: AISearch
 * @description: 颜色工厂
 * @author: zjj
 * @create: 2019-11-03 12:10:45
 **/
public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("red")) {
            return new Red();
        } else if (color.equalsIgnoreCase("blue")) {
            return new Blue();
        } else if (color.equalsIgnoreCase("green")) {
            return new Green();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
