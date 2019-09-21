package com.zjj.aisearch.mapper;


import com.zjj.aisearch.model.Article;
import com.zjj.aisearch.model.Item;
import com.zjj.aisearch.model.SearchRecord;
import com.zjj.aisearch.model.User;

import java.util.List;

public interface IndexMapper  {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);

    List<Article> search(String keyword);
    List<Item> searchItem(String keyword);
}
