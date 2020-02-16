package com.zjj.aisearch.repository;


import com.zjj.aisearch.pojo.dto.MovieDTO;
import io.searchbox.client.JestResult;

public interface IMovieRepository {

    boolean save(MovieDTO movieDTO);

    JestResult deleteDocument(String index, String type, String id);

    // 删除index
    void deleteIndex(String index);
}
