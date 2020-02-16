package com.zjj.aisearch.repository.impl;

import com.zjj.aisearch.pojo.dto.MovieDTO;
import com.zjj.aisearch.repository.IMovieRepository;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.indices.DeleteIndex;
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
    //创建索引
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


    //删除文档

    /**
     * 根据主键删除文档
     *
     * @param index 待操作的库
     * @param type  待操作的表
     * @param id    待操作的主键id
     * @return
     */
    @Override
    public JestResult deleteDocument(String index, String type, String id) {
        Delete delete = new Delete.Builder(id).index(index).type(type).build();
        JestResult result = null;
        try {
            result = client.execute(delete);
            log.info("deleteDocument == " + result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 删除index
    @Override
    public void deleteIndex(String index) {
        try {
            JestResult jestResult = client.execute(new DeleteIndex.Builder(index).build());
            System.out.println("deleteIndex result:{}" + jestResult.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
