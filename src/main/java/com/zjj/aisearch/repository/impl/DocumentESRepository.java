package com.zjj.aisearch.repository.impl;

import com.zjj.aisearch.pojo.dto.DocumentDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.repository.IDocumentRepository;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.DeleteIndex;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Repository
@Slf4j
public class DocumentESRepository implements IDocumentRepository {

    public static final String INDEX = "document";

    public static final String TYPE = "document";

    @Autowired
    private JestClient client;

    //数据库导入到索引库
    //创建索引
    @Override
    public boolean save(DocumentDTO documentDTO) {
        //创建索引
        Index index = new Index.Builder(documentDTO).index(INDEX).type(TYPE).build();
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

    // 查询
    @Override
    public Page<DocumentDTO> query(String queryString, int pageNo, int size) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false).tagsSchema("default");
        searchSourceBuilder.highlighter(highlightBuilder);
        QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(queryString);
        searchSourceBuilder.query(queryStringQueryBuilder).from(from(pageNo, size)).size(size);
        log.info("搜索DSL:{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(INDEX)
                .addType(TYPE)
                .build();
        try {
            SearchResult result = client.execute(search);
            List<SearchResult.Hit<DocumentDTO, Void>> hits = result.getHits(DocumentDTO.class);
            List<DocumentDTO> articles = hits.stream().map(hit -> {
                DocumentDTO article = hit.source;
                Map<String, List<String>> highlight = hit.highlight;
                if (highlight.containsKey("documentcontent")) {
                    /*article.setFileContent(highlight.get("fileContent").get(0) + " [score]-->" + hit.score);*/
                    article.setDocumentcontent(highlight.get("documentcontent").get(0));
                }
                if (highlight.containsKey("documentname")) {
                    /*article.setFileName(highlight.get("fileName").get(0) + " [score]-->" + hit.score);*/
                    article.setDocumentname(highlight.get("documentname").get(0));
                }
                return article;
            }).collect(toList());
            int took = result.getJsonObject().get("took").getAsInt();
            Page<DocumentDTO> page = Page.<DocumentDTO>builder().list(articles).pageNo(pageNo).size(size).total(result.getTotal()).took(took).build();
            return page;
        } catch (IOException e) {
            log.error("search异常", e);
            return null;
        }
    }

    private int from(int pageNo, int size) {
        return (pageNo - 1) * size < 0 ? 0 : (pageNo - 1) * size;
    }
}
