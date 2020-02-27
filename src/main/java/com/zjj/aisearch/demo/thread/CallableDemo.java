package com.zjj.aisearch.demo.thread;

import java.util.concurrent.Callable;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-02-24 21:26:00
 **/
public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "haha";
    }
}
