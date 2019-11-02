package com.zjj.aisearch.demo.patternDesign.factory;

/**
 * @program: AISearch
 * @description: 图形工厂
 * @author: zjj
 * @create: 2019-11-03 00:50:16
 **/
public class ShapeFactory {

    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        }
        if (shapeType.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        }
        if (shapeType.equalsIgnoreCase("square")) {
            return new Square();
        }
        return null;
    }
}
