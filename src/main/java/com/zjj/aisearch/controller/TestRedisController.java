package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: AISearch
 * @description: 测试RedisController
 * @author: zjj
 * @create: 2020-02-08 17:56:59
 **/
@RestController
public class TestRedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/testredis")
    public ResponseResult testredis() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(0);
        responseResult.setMsg(stringRedisTemplate.opsForValue().get("aaa"));
        return responseResult;
    }
}
