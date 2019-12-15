package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.ImgMapper;
import com.zjj.aisearch.model.Img;
import com.zjj.aisearch.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: AISearch
 * @description: 图片上传Service
 * @author: zjj
 * @create: 2019-12-15 12:18:01
 **/
@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    private ImgMapper imgMapper;

    @Override
    public int save(Img img) {
        return imgMapper.save(img);
    }
}
