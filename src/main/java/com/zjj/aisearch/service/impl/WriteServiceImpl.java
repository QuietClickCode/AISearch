package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.DocumentMapper;
import com.zjj.aisearch.mapper.WriteMapper;
import com.zjj.aisearch.model.Editor;
import com.zjj.aisearch.model.MarkDown;
import com.zjj.aisearch.pojo.dto.DocumentDTO;
import com.zjj.aisearch.service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-05 02:52:40
 **/
@Service
public class WriteServiceImpl implements WriteService {
    @Autowired
    private WriteMapper writeMapper;

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public int saveMarkDown(MarkDown markDown) {
        return writeMapper.saveMarkDown(markDown);
    }

    @Override
    public int saveEditor(Editor editor) {
        return writeMapper.saveEditor(editor);
    }
    @Override
    public int saveDocument(DocumentDTO documentDTO) {
        return documentMapper.insert(documentDTO);
    }


}
