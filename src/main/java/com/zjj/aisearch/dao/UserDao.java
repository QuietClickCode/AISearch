package com.zjj.aisearch.dao;

import com.zjj.aisearch.pojo.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDao extends ElasticsearchRepository<User, Integer> {
}