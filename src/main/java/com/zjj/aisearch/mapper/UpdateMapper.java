package com.zjj.aisearch.mapper;

import com.zjj.aisearch.model.Editor;
import com.zjj.aisearch.model.MarkDown; /**
* @Description:
* @Param:  
* @return:  
* @Author: zjj
* @Date: 2019/10/27 
*/ 
public interface UpdateMapper {
    Integer updateEditor(Editor editor);

    Integer deleteEditor(Integer id);

    Integer updateMarkdown(MarkDown markdown);

    Integer deleteMarkdown(Integer id);
}
