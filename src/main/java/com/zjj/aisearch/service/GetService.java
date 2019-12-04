package com.zjj.aisearch.service;

import com.zjj.aisearch.model.Todo;

import java.util.List;
import java.util.Map;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-12-04 20:48:40
 **/
public interface GetService {
    List<Todo> getTodoList(Map<String, String> map);
}
