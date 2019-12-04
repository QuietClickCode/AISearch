package com.zjj.aisearch.mapper;

import com.zjj.aisearch.model.Todo;

import java.util.List;
import java.util.Map;

public interface GetMapper {
    List<Todo> getTodoList(Map<String, String> map);
}
