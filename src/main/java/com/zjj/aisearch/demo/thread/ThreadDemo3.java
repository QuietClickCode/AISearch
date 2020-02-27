package com.zjj.aisearch.demo.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-02-24 19:42:08
 **/
public class ThreadDemo3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> submit = executorService.submit(new CallableDemo()
        );
        String s = submit.get();
        System.out.println(s);
    }
}
