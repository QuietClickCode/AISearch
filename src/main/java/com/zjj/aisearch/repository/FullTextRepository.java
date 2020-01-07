package com.zjj.aisearch.repository;


import com.zjj.aisearch.pojo.dto.FullTextDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;
import io.searchbox.client.JestResult;

public interface FullTextRepository {

    boolean save(FullTextDTO fullTextDTO);

    public JestResult deleteDocument(String index, String type, String id);

    Page<FullTextDTO> query(String queryString, int pageNo, int size);

    Page<FullTextDTO> query(QueryDTO queryDTO, int pageNo, int size);

    FullTextDTO get(String id);
}
