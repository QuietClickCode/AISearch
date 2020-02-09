package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.Editor;
import com.zjj.aisearch.model.MarkDown;
import com.zjj.aisearch.service.UpdateService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: AISearch
 * @description: 更新
 * @author: zjj
 * @create: 2019年10月27日21:47:34
 **/
@RestController
@Slf4j
public class UpdateController {

    @Autowired
    private UpdateService updateServiceImpl;

    @PostMapping("updateeditor")
    @ApiOperation("更新editor")
    public Integer updateEditor(@RequestBody Map<String, Object> map) {
        String title = (String) map.get("title");
        String content = (String) map.get("content");
        Integer id = (Integer) map.get("id");
        Editor editor = new Editor();
        editor.setTitle(title);
        editor.setContent(content);
        editor.setId(id);
        return updateServiceImpl.updateEditor(editor);
    }

    @PostMapping("deleteeditor")
    @ApiOperation("删除editor")
    public Integer deleteEditor(@RequestBody Map<String, Object> map) {
        Integer id = (Integer) map.get("id");
        return updateServiceImpl.deleteEditor(id);
    }
    @PostMapping("updatemarkdown")
    @ApiOperation("更新markdown")
    public Integer updateMarkdown(@RequestBody Map<String, Object> map) {
        String title = (String) map.get("title");
        String content = (String) map.get("content");
        Integer id = (Integer) map.get("id");
        MarkDown markdown = new MarkDown();
        markdown.setTitle(title);
        markdown.setContent(content);
        markdown.setId(id);
        return updateServiceImpl.updateMarkdown(markdown);
    }

    @PostMapping("deletemarkdown")
    @ApiOperation("删除markdown")
    public Integer deleteMarkdown(@RequestBody Map<String, Object> map) {
        Integer id = (Integer) map.get("id");
        return updateServiceImpl.deleteMarkdown(id);
    }

    @PostMapping("deleteAINote")
    @ApiOperation("删除便签")
    public Integer deleteAINote(@RequestBody Map<String, Object> map) {
        Integer id = (Integer) map.get("id");
        return updateServiceImpl.deleteAINote(id);
    }

}
