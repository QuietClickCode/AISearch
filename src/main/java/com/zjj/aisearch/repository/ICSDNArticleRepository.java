package com.zjj.aisearch.repository;


import com.zjj.aisearch.pojo.dto.CSDNArticleDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;

public interface ICSDNArticleRepository {

    boolean save(CSDNArticleDTO csdnArticleDTO);

    Page<CSDNArticleDTO> query(String queryString, int pageNo, int size);

    Page<CSDNArticleDTO> query(QueryDTO queryDTO, int pageNo, int size);

    CSDNArticleDTO get(String id);
}
