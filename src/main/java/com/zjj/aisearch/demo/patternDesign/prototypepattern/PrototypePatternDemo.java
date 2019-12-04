package com.zjj.aisearch.demo.patternDesign.prototypepattern;

/**
 * @program: AISearch
 * @description: 原型模式
 * @author: zjj
 * @create: 2019-11-03 12:21:32
 **/
public class PrototypePatternDemo  {
    /**
     * 原型模式
     * 用于创建重复对象,还能保证性能,
     */

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape cloneShape = ShapeCache.getShape("1");
        System.out.println("shape: " + cloneShape.getType());
        Shape cloneShape2 = ShapeCache.getShape("2");
        System.out.println("shape: " + cloneShape2.getType());
        Shape cloneShape3 = ShapeCache.getShape("3");
        System.out.println("shape: " + cloneShape3.getType());


    }

}
