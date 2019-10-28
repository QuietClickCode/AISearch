package com.zjj.aisearch.repository;


import com.zjj.aisearch.pojo.dto.JianShuArticleDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;

public interface IJianShuArticleRepository {

    boolean save(JianShuArticleDTO jianShuArticleDTO);

    Page<JianShuArticleDTO> query(String queryString, int pageNo, int size);

    Page<JianShuArticleDTO> query(QueryDTO queryDTO, int pageNo, int size);

    JianShuArticleDTO get(String id);
}
