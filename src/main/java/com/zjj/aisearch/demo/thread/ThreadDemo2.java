package com.zjj.aisearch.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-02-24 19:42:08
 **/
public class ThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Object> submit = (Future<Object>) executorService.submit(new ThreadPoolRunnable());
        Future<Object> submit2 = (Future<Object>) executorService.submit(new ThreadPoolRunnable());

    }
}
