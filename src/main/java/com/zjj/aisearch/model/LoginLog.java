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
 * @description: 搜索记录
 * @author: zjj
 * @create: 2019-09-07 23:19:11
 **/
@Data
@Getter
@Setter
@ToString
public class LoginLog {

    private Integer id;
    private Integer userId ;
    private String createtime;
    private String locationId;
    private String browserInfoId;

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
