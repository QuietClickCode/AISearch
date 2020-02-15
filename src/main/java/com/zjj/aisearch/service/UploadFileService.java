package com.zjj.aisearch.service;

import com.zjj.aisearch.pojo.dto.FullTextDTO;

public interface UploadFileService {
    int save(FullTextDTO fullTextDTO);

    int deleteFile(Integer id);
}
