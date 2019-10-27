package com.zjj.aisearch.repository;


import com.zjj.aisearch.pojo.dto.DouBanMovieDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;

public interface IDouBanMovieRepository {

    boolean save(DouBanMovieDTO movie);

    Page<DouBanMovieDTO> query(String queryString, int pageNo, int size);

    Page<DouBanMovieDTO> query(QueryDTO queryDTO, int pageNo, int size);

    DouBanMovieDTO get(String id);
}
