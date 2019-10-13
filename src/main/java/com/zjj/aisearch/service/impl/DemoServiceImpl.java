package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.DemoMapper;
import com.zjj.aisearch.model.EmpList;
import com.zjj.aisearch.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-05 02:52:40
 **/
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;


    @Override
    public List<EmpList> queryEmpList() {
        return demoMapper.queryEmpList();
    }
}
