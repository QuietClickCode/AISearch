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
 * @description: 系统操作日志
 * @author: zjj
 * @create: 2019-10-02 00:09:12
 **/
@Data
@Getter
@Setter
@ToString
public class SystemLog {

    private Integer id;
    private String createtime;
    private String operation;
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
