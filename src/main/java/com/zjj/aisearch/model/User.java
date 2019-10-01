package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: aisearch
 * @description:
 * @author: zjj
 * @create: 2019-09-07 17:37:20
 **/
@Getter
@Setter
@ToString
@Data
public class User {

    private String id;
    private String username;
    private String password;
    private String createtime;
    private String browserInfoId;
    private String locationId;

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
