package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.UpdateMapper;
import com.zjj.aisearch.model.Editor;
import com.zjj.aisearch.model.MarkDown;
import com.zjj.aisearch.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019年10月27日21:50:48
 **/
@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private UpdateMapper updateMapper;


    @Override
    public Integer updateEditor(Editor editor) {
        return updateMapper.updateEditor(editor);
    }

    @Override
    public Integer deleteEditor(Integer id) {
        return updateMapper.deleteEditor(id);
    }

    @Override
    public Integer updateMarkdown(MarkDown markdown) {
        return updateMapper.updateMarkdown(markdown);
    }

    @Override
    public Integer deleteMarkdown(Integer id) {
        return updateMapper.deleteMarkdown(id);
    }

    @Override
    public Integer deleteAINote(Integer id) {
        return updateMapper.deleteAINote(id);
    }
}
