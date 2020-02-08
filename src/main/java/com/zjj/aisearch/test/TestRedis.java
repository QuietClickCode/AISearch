package com.zjj.aisearch.test;

import com.alibaba.fastjson.JSONArray;
import com.zjj.aisearch.service.impl.QueryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: AISearch
 * @description: 测试Redis 测试的时候要加springboot上下文环境
 * @author: zjj
 * @create: 2020-02-08 17:43:46
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private QueryServiceImpl queryServiceImpl;
    @Test
    public void test() {
        List<String> strings = queryServiceImpl.querySystem();
        stringRedisTemplate.opsForValue().set("aaa", JSONArray.toJSON(strings).toString());
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        List<String> aaa = JSONArray.parseArray(stringRedisTemplate.opsForValue().get("aaa"), String.class);
        System.out.println(aaa);


    }


}
