package com.zjj.aisearch.service;

import com.zjj.aisearch.model.Editor;
import com.zjj.aisearch.model.MarkDown;
import com.zjj.aisearch.pojo.dto.DocumentDTO;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-05 02:51:40
 **/
public interface WriteService {



    int saveMarkDown(MarkDown markDown);

    int saveEditor(Editor editor);

    int saveDocument(DocumentDTO documentDTO);
}
