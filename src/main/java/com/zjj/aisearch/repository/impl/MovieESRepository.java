package com.zjj.aisearch.repository.impl;

import com.zjj.aisearch.pojo.dto.MovieDTO;
import com.zjj.aisearch.repository.IMovieRepository;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@Slf4j
public class MovieESRepository implements IMovieRepository {

    public static final String INDEX = "movie";

    public static final String TYPE = "movie";

    @Autowired
    private JestClient client;

    //数据库导入到索引库
    @Override
    public boolean save(MovieDTO movieDTO) {
        //创建索引
        Index index = new Index.Builder(movieDTO).index(INDEX).type(TYPE).build();
        //返回的结果
        JestResult jestResult = null;
        try {
            jestResult = client.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("save返回结果{}", jestResult.getJsonString());
        return true;
    }
}
