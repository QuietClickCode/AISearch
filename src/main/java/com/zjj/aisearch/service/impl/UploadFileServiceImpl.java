package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.UploadFileMapper;
import com.zjj.aisearch.pojo.dto.FullTextDTO;
import com.zjj.aisearch.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: AISearch
 * @description: 上传文件
 * @author: zjj
 * @create: 2020-01-06 14:06:14
 **/
@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Override
    public int save(FullTextDTO fullTextDTO) {
        return uploadFileMapper.save(fullTextDTO);
    }
}
