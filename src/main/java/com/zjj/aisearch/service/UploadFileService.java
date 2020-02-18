package com.zjj.aisearch.service;

import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.pojo.dto.FullTextDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    int save(FullTextDTO fullTextDTO);

    int deleteFile(Integer id);

    ResponseResult uploadlocal(MultipartFile file) throws Exception;


}
