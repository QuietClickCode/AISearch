package com.zjj.aisearch.pojo.dto;

import io.searchbox.annotations.JestId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjj
 * @description
 * @date 2019年10月28日15:01:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BKYArticleDTO {
    @JestId
    private String id;

    private String title;

    private String content;

    private String createtime;

}
