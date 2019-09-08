package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.SearchRecord;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: aisearch
 * @description: 入口
 * @author: zjj
 * @create: 2019-09-07 16:53:15
 **/
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;


    @RequestMapping("index")
    public String index(Model model, String keyword) {
        return "index";
    }

    @RequestMapping("search")
    @ResponseBody
    public void search(String keyword) {
        SearchRecord searchRecord = new SearchRecord();
        searchRecord.setKeyword(keyword);
        searchRecord.setSearchTime(DateConvert.getTime());

        int i = indexService.insertSearchRecord(searchRecord);
        System.out.println(i);
    }


}
