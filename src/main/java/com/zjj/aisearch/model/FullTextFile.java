package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 图片
 * @author: zjj
 * @create: 2019-12-15 12:22:31
 **/
@Data
@Getter
@Setter
@ToString
public class FullTextFile {

    private Integer id;

    private String fileName;

    private String fileType;
    private Long fileSize;
    private String fileContent;
    private String filePath;

    private String createuser;
    private String createtime;
}
