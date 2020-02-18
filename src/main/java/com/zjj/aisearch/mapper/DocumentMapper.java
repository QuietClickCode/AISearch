package com.zjj.aisearch.mapper;

import com.zjj.aisearch.pojo.dto.DocumentDTO;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DocumentMapper extends Mapper<DocumentDTO> {
    @Select("select * from document where documentcontent = #{content}")
    List<DocumentDTO> selectByContent(String content);
}