package com.zjj.aisearch.crawler;

import com.alibaba.fastjson.JSON;
import com.zjj.aisearch.model.Movie;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.GetJson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Main {

    @Autowired
    private IndexService indexServiceImpl;

    @Test
    public void test() {


        int start;//每页多少条
        int total = 0;//记录数
        int end = 9979;//总共9979条数据
        for (start = 0; start <= end; start += 20) {
            try {

                String address = "https://Movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=" + start;

                JSONObject dayLine = new GetJson().getHttpJson(address, 1);

                System.out.println("start:" + start);
                JSONArray json = dayLine.getJSONArray("data");
                List<Movie> list = JSON.parseArray(json.toString(), Movie.class);

                System.exit(0);
                if (start <= end) {
                    System.out.println("已经爬取到底了");
                }
                for (Movie movie : list) {
                    indexServiceImpl.insertMovie(movie);
                }
                total += list.size();
                System.out.println("正在爬取中---共抓取:" + total + "条数据");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
