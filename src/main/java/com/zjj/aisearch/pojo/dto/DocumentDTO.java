package com.zjj.aisearch.pojo.dto;

import io.searchbox.annotations.JestId;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zjj
 * @description 文档
 * @date 2020年2月16日20:41:13
 */
@Data
@Table(name = "document")
public class DocumentDTO {
    //相当于数据库的列字段
    //控制es中的_id=这个id;
    @JestId
    @Id
    private Integer id;

    private String documentname;

    private String documentcontent;
}
