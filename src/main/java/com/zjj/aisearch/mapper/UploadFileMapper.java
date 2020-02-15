package com.zjj.aisearch.mapper;

import com.zjj.aisearch.pojo.dto.FullTextDTO;

public interface UploadFileMapper {
    int save(FullTextDTO fullTextDTO);

    int deleteFile(Integer id);
}
