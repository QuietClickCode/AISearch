package com.zjj.aisearch.mapper;


import com.zjj.aisearch.model.SearchRecord;
import com.zjj.aisearch.model.User;

public interface IndexMapper  {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);
}
