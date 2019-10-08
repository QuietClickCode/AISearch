package com.zjj.aisearch.mapper;

import com.zjj.aisearch.model.Editor;
import com.zjj.aisearch.model.MarkDown;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-05 02:52:09
 **/
public interface WriteMapper {


    int saveEditor(Editor editor);

    int saveMarkDown(MarkDown markDown);

}
