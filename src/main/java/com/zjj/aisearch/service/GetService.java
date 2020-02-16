package com.zjj.aisearch.service;

import com.zjj.aisearch.model.Todo;
import com.zjj.aisearch.pojo.dto.DocumentDTO;
import com.zjj.aisearch.pojo.dto.MovieDTO;

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

    List<MovieDTO> getMovieDTOList();

    List<DocumentDTO> getDocumentDTOList();
}
