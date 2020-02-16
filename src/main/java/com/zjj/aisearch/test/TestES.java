package com.zjj.aisearch.test;

import com.zjj.aisearch.pojo.dto.MovieDTO;
import com.zjj.aisearch.repository.IMovieRepository;
import com.zjj.aisearch.service.GetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: AISearch
 * @description: 测试Redis 测试的时候要加springboot上下文环境
 * @author: zjj
 * @create: 2020-02-08 17:43:46
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestES {

    @Autowired
    private IMovieRepository movieESRepository;

    @Autowired
    private GetService getService;

    //导入到es
    //创建索引
    @Test
    public void test() {
        List<MovieDTO> movieDTOList = getService.getMovieDTOList();
        for (MovieDTO movieDTO : movieDTOList) {
            movieESRepository.save(movieDTO);
        }
    }

    //删除索引
    @Test
    public void test1() {
        movieESRepository.deleteIndex("movie");
    }


}
