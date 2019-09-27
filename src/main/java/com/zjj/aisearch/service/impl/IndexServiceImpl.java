package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.IndexMapper;
import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: aisearch
 * @description:
 * @author: zjj
 * @create: 2019-09-07 17:17:08
 **/

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;

    @Override
    public User index() {
        return indexMapper.index();
    }

    @Override
    public int insertSearchRecord(SearchRecord searchRecord) {
        return indexMapper.insertSearchRecord(searchRecord);
    }

    @Override
    public List<Article> search(String keyword) {
        return indexMapper.search(keyword);
    }

    @Override
    public List<Item> searchItem(String keyword) {

        return indexMapper.searchItem(keyword);
    }

    @Override
    public int insertBrowserInfo(BrowserInfo browserInfo) {
        return indexMapper.insertBrowserInfo(browserInfo);
    }

    @Override
    public int insertLocation(Location location) {
        return indexMapper.insertLocation(location);
    }

    @Override
    public List<SearchRecordLocation> selectSearchRecordLocation() {
        return indexMapper.selectSearchRecordLocation();
    }

}
