package com.zjj.aisearch.repository.impl;

import com.zjj.aisearch.pojo.dto.FullTextDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;
import com.zjj.aisearch.repository.FullTextRepository;
import com.zjj.aisearch.utils.JDBCUtils;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Description: 全文搜索
 * @Param:
 * @return:
 * @Author: zjj
 * @Date: 2020/1/6
 */
@Repository
@Slf4j
public class FullTextESRepository implements FullTextRepository {

    //索引名称 数据库
    public static final String INDEX = "fulltextfile";

    //类型 表
    public static final String TYPE = "file";

    @Autowired
    private JestClient client;
    private static QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

    //数据库导入到索引库
    @Override
    public boolean save(FullTextDTO fullTextDTO) {
        System.out.println("-------------------------------");
        /*FullTextFile fullTextFile = new FullTextFile();
        String sql = null;
        String sql2 = null;
        sql = "select * from ai_file";
        List<Object[]> query = null;
        try {
            query = qr.query(sql, new ArrayListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Object[] qiang:query){
            fullTextFile.setId((Integer) qiang[0]);
            fullTextFile.setCreatetime(DateTimeUtil.dateToStr((Date) qiang[1]));
            fullTextFile.setFileType((String) qiang[2]);
            fullTextFile.setFileSize((Long) qiang[3]);
            fullTextFile.setFileContent((String) qiang[4]);
            fullTextFile.setFilePath((String) qiang[5]);
            fullTextFile.setCreateuser((String) qiang[6]);
            fullTextFile.setFileName((String) qiang[7]);
            System.out.println(fullTextFile);
            fullTextDTO.setId(fullTextFile.getId());
            fullTextDTO.setFileType(fullTextFile.getFileType());
            fullTextDTO.setFilePath(fullTextFile.getFilePath());
            fullTextDTO.setFileName(fullTextFile.getFileName());
            fullTextDTO.setFileContent(fullTextFile.getFileContent());
            fullTextDTO.setCreateuser(fullTextFile.getCreateuser());
            fullTextDTO.setCreatetime(fullTextFile.getCreatetime());
            Index index = new Index.Builder(fullTextFile).index(INDEX).type(TYPE).build();
            try {
                JestResult jestResult = client.execute(index);
                log.info("save返回结果{}", jestResult.getJsonString());
            } catch (IOException e) {
                log.error("save异常", e);
            }
        }*/
        Index index = new Index.Builder(fullTextDTO).index(INDEX).type(TYPE).build();
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
     * @param index 待操作的库
     * @param type  待操作的表
     * @param id    待操作的主键id
     * @return
     */
    public JestResult deleteDocument(String index, String type, String id) {
        Delete delete = new Delete.Builder(id).index(index).type(type).build();
        JestResult result = null ;
        try {
            result = client.execute(delete);
            log.info("deleteDocument == " + result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    @Override
    public Page<FullTextDTO> query(String queryString, int pageNo, int size) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(queryString);
        searchSourceBuilder.query(queryStringQueryBuilder).from(from(pageNo, size)).size(size);
        log.info("搜索DSL:{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(INDEX)
                .addType(TYPE)
                .build();
        try {
            SearchResult result = client.execute(search);
            List<SearchResult.Hit<FullTextDTO, Void>> hits = result.getHits(FullTextDTO.class);
            List<FullTextDTO> articles = hits.stream().map(hit -> {
                FullTextDTO article = hit.source;
                return article;
            }).collect(toList());
            int took = result.getJsonObject().get("took").getAsInt();
            Page<FullTextDTO> page = Page.<FullTextDTO>builder().list(articles).pageNo(pageNo).size(size).total(result.getTotal()).took(took).build();
            return page;
            /*List<SearchResult.Hit<FullTextDTO, Void>> hits = result.getHits(FullTextDTO.class);
            List<FullTextDTO> articles = hits.stream().map(hit -> {
                FullTextDTO article = hit.source;
                Map<String, List<String>> highlight = hit.highlight;
                if (highlight.containsKey("title")) {
                    article.setTitle(highlight.get("title").get(0) + " [score]-->" + hit.score);
                }
                if (highlight.containsKey("content")) {
                    article.setContent(highlight.get("content").get(0));
                }
                return article;
            }).collect(toList());
            int took = result.getJsonObject().get("took").getAsInt();
            Page<FullTextDTO> page = Page.<FullTextDTO>builder().list(articles).pageNo(pageNo).size(size).total(result.getTotal()).took(took).build();
            return page;*/
        } catch (IOException e) {
            log.error("search异常", e);
            return null;
        }
    }

    @Override
    public Page<FullTextDTO> query(QueryDTO queryDTO, int pageNo, int size) {
        /*SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().from(from(pageNo, size)).size(size);
        if (queryDTO.getMinScore() != null) {
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("score").gte(queryDTO.getMinScore());
            boolQueryBuilder.must(rangeQueryBuilder);
            searchSourceBuilder.query(boolQueryBuilder);
        }
        if (queryDTO.getOrderBy() != null) {
            searchSourceBuilder.sort(queryDTO.getOrderBy(), SortOrder.DESC);
        }
        log.debug("搜索DSL:{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(INDEX)
                .addType(TYPE)
                .build();
        try {
            SearchResult result = client.execute(search);
            List<FullTextDTO> articles = result.getSourceAsObjectList(FullTextDTO.class, false);
            int took = result.getJsonObject().get("took").getAsInt();
            Page<FullTextDTO> page = Page.<FullTextDTO>builder().list(articles).pageNo(pageNo).size(size).total(result.getTotal()).took(took).build();
            return page;
        } catch (IOException e) {
            log.error("search异常", e);
            return null;
        }*/
        return null;

    }

    @Override
    public FullTextDTO get(String id) {
       /* Get get = new Get.Builder(INDEX, id).type(TYPE).build();
        try {
            JestResult result = client.execute(get);
            FullTextDTO article = result.getSourceAsObject(FullTextDTO.class);
            return article;
        } catch (IOException e) {
            log.error("get异常", e);
            return null;
        }*/
        return null;
    }

    private int from(int pageNo, int size) {
        return (pageNo - 1) * size < 0 ? 0 : (pageNo - 1) * size;
    }
}
