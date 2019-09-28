package com.zjj.aisearch.mapper;


import com.zjj.aisearch.model.*;

import java.util.List;

public interface IndexMapper  {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);

    Article search(int keyword);
    List<Item> searchItem(String keyword);

    int insertBrowserInfo(BrowserInfo browserInfo);

    int insertLocation(Location location);

    List<SearchRecordLocation> selectSearchRecordLocation();

}
