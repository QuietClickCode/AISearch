package com.zjj.aisearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: aisearch
 * @description: 启动类
 * @author: zjj
 * @create: 2019-09-07 16:49:19
 **/

@SpringBootApplication
@MapperScan(basePackages = "com.zjj.aisearch.mapper")
public class SpringbootApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringbootApplication.class, args);
      /*  MyProcessor myProcessor = new MyProcessor();

        myProcessor.run();*/

    }
}
