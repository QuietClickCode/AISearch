package com.zjj.aisearch.repository.impl;

import com.zjj.aisearch.pojo.dto.BKYArticleDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;
import com.zjj.aisearch.repository.IBKYArticleRepository;
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
public class BKYArticleESRepository implements IBKYArticleRepository {

    public static final String INDEX = "bky";

    public static final String TYPE = "article";

    @Autowired
    private JestClient client;

    @Override
    public boolean save(BKYArticleDTO bkyArticleDTO) {
        Index index = new Index.Builder(bkyArticleDTO).index(INDEX).type(TYPE).build();
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
    public Page<BKYArticleDTO> query(String queryString, int pageNo, int size) {
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
            List<SearchResult.Hit<BKYArticleDTO, Void>> hits = result.getHits(BKYArticleDTO.class);
            List<BKYArticleDTO> articles = hits.stream().map(hit -> {
                BKYArticleDTO article = hit.source;
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
            Page<BKYArticleDTO> page = Page.<BKYArticleDTO>builder().list(articles).pageNo(pageNo).size(size).total(result.getTotal()).took(took).build();
            return page;
        } catch (IOException e) {
            log.error("search异常", e);
            return null;
        }

    }

    @Override
    public Page<BKYArticleDTO> query(QueryDTO queryDTO, int pageNo, int size) {
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
            List<BKYArticleDTO> articles = result.getSourceAsObjectList(BKYArticleDTO.class, false);
            int took = result.getJsonObject().get("took").getAsInt();
            Page<BKYArticleDTO> page = Page.<BKYArticleDTO>builder().list(articles).pageNo(pageNo).size(size).total(result.getTotal()).took(took).build();
            return page;
        } catch (IOException e) {
            log.error("search异常", e);
            return null;
        }

    }

    @Override
    public BKYArticleDTO get(String id) {
        Get get = new Get.Builder(INDEX, id).type(TYPE).build();
        try {
            JestResult result = client.execute(get);
            BKYArticleDTO article = result.getSourceAsObject(BKYArticleDTO.class);
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
