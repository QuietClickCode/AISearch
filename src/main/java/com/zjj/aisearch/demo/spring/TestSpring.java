package com.zjj.aisearch.demo.spring;

import com.zjj.aisearch.demo.annotations.ZjjAutowired;
import com.zjj.aisearch.demo.annotations.ZjjController;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-02-29 00:20:51
 **/
@ZjjController
public class TestSpring {

    @ZjjAutowired
    public SpringTestController springTestController;

    public void test() {
        springTestController.test();
    }
}
