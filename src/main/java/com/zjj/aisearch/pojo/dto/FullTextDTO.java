package com.zjj.aisearch.pojo.dto;

import io.searchbox.annotations.JestId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjj
 * @description 全文检索
 * @date 2019年10月28日15:01:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullTextDTO {
    //相当于数据库的列字段
    @JestId
    private Integer id;

    private String fileName;

    private String fileType;
    private Long fileSize;
    private String fileContent;
    private String filePath;

    private String createuser;
    private String createtime;

}
