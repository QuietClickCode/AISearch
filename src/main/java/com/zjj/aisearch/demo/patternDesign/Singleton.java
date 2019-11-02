package com.zjj.aisearch.demo.patternDesign;

/**
 * @program: AISearch
 * @description: 单例模式
 * @author: zjj
 * @create: 2019-11-03 00:16:13
 **/
public class Singleton {

    //单例模式的特点
    /**
     * 类构造器私有,就是说不能通过new的方式创建实例,只能通过暴露出来特定的api创建
     * 持有静态的表示自己类型的属性
     * 对外提供获取实例的静态方法
     */
    //懒汉模式
    /**
     * 线程不安全,延迟初始化,严格意义不是单例模式
     */
    /*private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/


    /**
     * 饿汉模式
     * 安全,常用,一开始就初始化,容易产生垃圾
     */
    /*private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }*/


    /**
     * 双重锁模式
     * 安全,延迟初始化,
     */
   /* private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }*/

    /**
     * 双重检查模式,进行了两次判空,
     * 第一次为了避免不必要的实例,
     * 第二次为了多线程下的同步,
     * volatile就是是为了避免jvm的重排序
     */

    /**
     * 静态内部类单例模式
     *
     */
    private Singleton() {}

    public static Singleton getInstance() {
        return Inner.instance;
    }

    private static class Inner {
        private static final Singleton instance = new Singleton();
    }

    /**
     * 只有第一次调用getInstance方法时,虚拟机才加载不初始化instance,
     * 并且只有一个线程可以获得对象的初始化锁,其他线程无法进行初始化,保证了对象的唯一性
     * 推荐用这个
     */

    /**
     * 理论上还有一种实现单例模式的方式,就是枚举单例模式,主要是用得比较少,了解即可
     */
}

