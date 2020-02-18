package com.zjj.aisearch.test;

import com.zjj.aisearch.mapper.DocumentMapper;
import com.zjj.aisearch.pojo.dto.DocumentDTO;
import com.zjj.aisearch.pojo.dto.JianShuArticleDTO;
import com.zjj.aisearch.pojo.dto.MovieDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.repository.IDocumentRepository;
import com.zjj.aisearch.repository.IJianShuArticleRepository;
import com.zjj.aisearch.repository.IMovieRepository;
import com.zjj.aisearch.service.GetService;
import com.zjj.aisearch.service.WriteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: AISearch
 * @description: 测试Redis 测试的时候要加springboot上下文环境
 * @author: zjj
 * @create: 2020-02-08 17:43:46
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
@Slf4j
public class TestES {

    @Autowired
    private IMovieRepository movieESRepository;

    @Autowired
    private IDocumentRepository documentESRepository;

    @Autowired
    private IJianShuArticleRepository jianShuArticleESRepository;

    @Autowired
    private GetService getService;

    @Autowired
    private WriteService writeService;

    @Autowired
    private DocumentMapper documentMapper;

    //导入到es
    //创建索引
    @Test
    public void test() {
        List<MovieDTO> movieDTOList = getService.getMovieDTOList();
        for (MovieDTO movieDTO : movieDTOList) {
            movieESRepository.save(movieDTO);
        }
    }

    //导入到es
    //创建document索引
    @Test
    public void test4() {
        List<DocumentDTO> dccumentDTOList = getService.getDocumentDTOList();
        for (DocumentDTO documentDTO : dccumentDTOList) {
            documentESRepository.save(documentDTO);
        }
    }

    //删除document索引

    @Test
    public void test8() {
        documentESRepository.deleteIndex("document");
    }


    //根据id删除文档
    @Test
    public void test9() {
        documentESRepository.deleteDocument("document", "document", "AXBUKVfs5MwoOe6VlozH");

    }

    //数据库和搜索库删除空内容数据
    @Test
    public void test10() {
        List<DocumentDTO> documentDTOS = documentMapper.selectByContent("");
        for (DocumentDTO documentDTO : documentDTOS) {
            documentESRepository.deleteDocument("document", "document", documentDTO.getId().toString());
            documentMapper.delete(documentDTO);
        }
        System.out.println(documentDTOS);
    }

    @Test
    public void test12() {
        DocumentDTO documentDTO = documentMapper.selectByPrimaryKey("51");
        System.out.println(documentDTO.toString());
        System.out.println(documentDTO.getDocumentcontent().toString().length());
    }

    //删除文件系统空内容
    @Test
    public void test11() {
        List<DocumentDTO> documentDTOS = documentMapper.selectAll();
        File files = new File("I:/document");
        File[] files1 = files.listFiles();
        ArrayList<String> haha = new ArrayList<>();
        ArrayList<String> hehe = new ArrayList<>();
        for (File file : files1) {
            String name = file.getName();
            haha.add(name);
        }
        for (DocumentDTO documentDTO : documentDTOS) {
            String documentname = documentDTO.getDocumentname();
            hehe.add(documentname);
        }
        for (String ha : haha) {
            boolean contains = hehe.contains(ha.toString());
            if (!contains) {
                File file = new File("I:/document/" + ha);
                boolean delete = file.delete();
                System.out.println(delete);
            }
        }
    }


    //导入文件到数据库中
    //方法有问题
    @Test
    public void test5() throws IOException, TikaException {

        File file = new File("I:\\document");
        File[] files = file.listFiles();
        Tika tika = new Tika();
        DocumentDTO documentDTO = new DocumentDTO();
        for (File f : files) {
            String filecontent = "";
            try {
                filecontent = tika.parseToString(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String name = f.getName();
            documentDTO.setDocumentname(name);
            Integer count = getService.getDocumentDTOByName(documentDTO);
            //intValue（）方法，意思是说，把Integer类型转化为Int类型。
            if (count.intValue() == 0) {
                documentDTO.setDocumentcontent(filecontent);
                writeService.saveDocument(documentDTO);
            }
        }
    }

    //测试避免重复文件入库
    @Test
    public void test7() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDocumentname("1576394941908_java.md");
        Integer count = getService.getDocumentDTOByName(documentDTO);
        System.out.println(count.intValue());

    }

    //删除索引
    @Test
    public void test1() {
        movieESRepository.deleteIndex("movie");
    }

    //查询简书
    @Test
    public void test2() {
        Page<JianShuArticleDTO> list = jianShuArticleESRepository.query("你好", 1, 10);
        for (JianShuArticleDTO l : list.getList()) {
            System.out.println(l.toString());
        }
    }

    //查询文件
    @Test
    public void test6() {
        Page<DocumentDTO> list = documentESRepository.query("乌合之众", 1, 10);
        for (DocumentDTO l : list.getList()) {
            System.out.println(l.toString());
        }
    }


}
