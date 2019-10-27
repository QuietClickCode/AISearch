package com.zjj.aisearch.service;

import com.zjj.aisearch.model.Editor;
import com.zjj.aisearch.model.MarkDown;

public interface UpdateService {
    Integer updateEditor(Editor editor);

    Integer deleteEditor(Integer id);
    Integer updateMarkdown(MarkDown markdown);

    Integer deleteMarkdown(Integer id);
}
