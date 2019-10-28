package com.zjj.aisearch.repository;


import com.zjj.aisearch.pojo.dto.BKYArticleDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;

public interface IBKYArticleRepository {

    boolean save(BKYArticleDTO bkyArticleDTO);

    Page<BKYArticleDTO> query(String queryString, int pageNo, int size);

    Page<BKYArticleDTO> query(QueryDTO queryDTO, int pageNo, int size);

    BKYArticleDTO get(String id);
}
