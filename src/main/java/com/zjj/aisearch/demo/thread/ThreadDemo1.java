package com.zjj.aisearch.demo.thread;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-02-24 19:42:08
 **/
public class ThreadDemo1 {
    public static void main(String[] args) throws InterruptedException {
        SubThread1 subThread = new SubThread1();
        Thread thread = new Thread(subThread);
        thread.start();
        System.out.println(thread.getName());
        new Thread() {
            public void run() {
                for (int i = 1; i < 100; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("new: " + i);
                }
            }
        }.start();
        new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("runable: " + i);
            }
        }).start();
        for (int i = 1; i < 100; i++) {
            Thread.sleep(10);
            System.out.println("main: " + i);
        }
    }
}
