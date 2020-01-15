package com.zjj.aisearch.repository.impl;

import com.zjj.aisearch.pojo.dto.JianShuArticleDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;
import com.zjj.aisearch.repository.IJianShuArticleRepository;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Repository
@Slf4j
public class JianShuArticleESRepository implements IJianShuArticleRepository {

    public static final String INDEX = "jianshu";

    public static final String TYPE = "article";

    @Autowired
    private JestClient client;

    @Override
    public boolean save(JianShuArticleDTO jianShuArticleDTO) {
        Index index = new Index.Builder(jianShuArticleDTO).index(INDEX).type(TYPE).build();
        try {
            JestResult jestResult = client.execute(index);
            log.info("save返回结果{}", jestResult.getJsonString());
            return jestResult.isSucceeded();
        } catch (IOException e) {
            log.error("save异常", e);
            return false;
        }
    }

    @Override
    public Page<JianShuArticleDTO> query(String queryString, int pageNo, int size) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false).tagsSchema("default");
        searchSourceBuilder.highlighter(highlightBuilder);
        QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(queryString);
        queryStringQueryBuilder
                .field("title", 10)
                .field("content");
        searchSourceBuilder.query(queryStringQueryBuilder).from(from(pageNo, size)).size(size);
        log.info("搜索DSL:{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(INDEX)
                .addType(TYPE)
                .build();
        try {
            SearchResult result = client.execute(search);
            List<SearchResult.Hit<JianShuArticleDTO, Void>> hits = result.getHits(JianShuArticleDTO.class);
            List<JianShuArticleDTO> articles = hits.stream().map(hit -> {
                JianShuArticleDTO article = hit.source;
                Map<String, List<String>> highlight = hit.highlight;
                if (highlight.containsKey("title")) {
                    /*article.setTitle(highlight.get("title").get(0) + " [score]-->" + hit.score);*/
                    article.setTitle(highlight.get("title").get(0));
                }
                if (highlight.containsKey("content")) {
                    /*article.setContent(highlight.get("content").get(0)+ " [score]-->" + hit.score);*/
                    article.setContent(highlight.get("content").get(0));
                }
                return article;
            }).collect(toList());
            int took = result.getJsonObject().get("took").getAsInt();
            Page<JianShuArticleDTO> page = Page.<JianShuArticleDTO>builder().list(articles).pageNo(pageNo).size(size).total(result.getTotal()).took(took).build();
            return page;
        } catch (IOException e) {
            log.error("search异常", e);
            return null;
        }

    }

    @Override
    public Page<JianShuArticleDTO> query(QueryDTO queryDTO, int pageNo, int size) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().from(from(pageNo, size)).size(size);
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
            List<JianShuArticleDTO> articles = result.getSourceAsObjectList(JianShuArticleDTO.class, false);
            int took = result.getJsonObject().get("took").getAsInt();
            Page<JianShuArticleDTO> page = Page.<JianShuArticleDTO>builder().list(articles).pageNo(pageNo).size(size).total(result.getTotal()).took(took).build();
            return page;
        } catch (IOException e) {
            log.error("search异常", e);
            return null;
        }

    }

    @Override
    public JianShuArticleDTO get(String id) {
        Get get = new Get.Builder(INDEX, id).type(TYPE).build();
        try {
            JestResult result = client.execute(get);
            JianShuArticleDTO article = result.getSourceAsObject(JianShuArticleDTO.class);
            return article;
        } catch (IOException e) {
            log.error("get异常", e);
            return null;
        }
    }

    private int from(int pageNo, int size) {
        return (pageNo - 1) * size < 0 ? 0 : (pageNo - 1) * size;
    }
}
