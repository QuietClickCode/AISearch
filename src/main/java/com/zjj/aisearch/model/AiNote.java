package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: AISearch
 * @description: 记录所思所想的便签
 * @author: zjj
 * @create: 2019-09-30 21:26:21
 **/
@Getter
@Setter
@Data
@ToString
public class AiNote {
    private Integer id;
    private String content;
    private String createtime;
    private Integer loginLogId;

    public void setCreatetime(String createtime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dd = null;
        try {
            dd = sdf.parse(createtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String d = sdf.format(dd);
        this.createtime = d;
    }
}
