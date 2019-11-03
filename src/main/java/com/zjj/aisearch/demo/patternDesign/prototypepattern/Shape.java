package com.zjj.aisearch.demo.patternDesign.prototypepattern;

/**
 * @program: AISearch
 * @description: 形状抽象类
 * @author: zjj
 * @create: 2019-11-03 12:25:44
 **/
public abstract class Shape implements Cloneable {

    private String id;

    protected String type;

    abstract void draw();

    public String getType() {
        return type;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
