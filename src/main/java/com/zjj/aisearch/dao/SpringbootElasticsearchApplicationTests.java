package com.zjj.aisearch.dao;

import com.zjj.aisearch.pojo.Article;
import com.zjj.aisearch.pojo.Author;
import com.zjj.aisearch.pojo.Tutorial;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticsearchApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    private ArticleSearchRepository articleSearchRepository;
    @Test
    public void testSaveArticleIndex(){
        Author author = new Author();
        author.setId(2L);
        author.setName("slp");
        author.setRemark("test");

        Tutorial tutorial = new Tutorial();
        tutorial.setId(2L);
        tutorial.setName("elastic search");

        Article article = new Article();
        article.setId(2L);
        article.setTitle("spring boot  elasticsearch");
        article.setAbstracts(" is very easy");
        article.setTutorial(tutorial);
        article.setAuthor(author);
        article.setContent(" based on lucene");
        article.setPostTime("20180704");
        article.setClickCount("1");
        articleSearchRepository.save(article);

    }

    @Test
    public void testSearch(){
        String queryString="spring";//搜索关键字
        QueryStringQueryBuilder builder=new QueryStringQueryBuilder(queryString);
        Iterable<Article> searchResult = articleSearchRepository.search(builder);
        Iterator<Article> iterator = searchResult.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getAbstracts());
        }
    }
}