package com.zjj.aisearch.repository;


import com.zjj.aisearch.pojo.dto.DocumentDTO;
import com.zjj.aisearch.pojo.entity.Page;
import io.searchbox.client.JestResult;

public interface IDocumentRepository {

    boolean save(DocumentDTO documentDTO);

    JestResult deleteDocument(String index, String type, String id);

    // 删除index
    void deleteIndex(String index);

    // 查询
    Page<DocumentDTO> query(String queryString, int pageNo, int size);
}
