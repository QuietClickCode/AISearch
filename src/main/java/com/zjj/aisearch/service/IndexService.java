package com.zjj.aisearch.service;

import com.zjj.aisearch.model.*;

import java.util.List;

/**
 * @program: aisearch
 * @description: service
 * @author: zjj
 * @create: 2019-09-07 17:16:18
 **/
public interface IndexService {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);

    List<Article> search(String keyword);
    List<Item> searchItem(String keyword);

    int insertBrowserInfo(BrowserInfo browserInfo);

    int insertLocation(Location location);


    List<SearchRecordLocation> selectSearchRecordLocation();
}
