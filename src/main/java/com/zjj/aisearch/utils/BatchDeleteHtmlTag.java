package com.zjj.aisearch.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

/**
 * @program: AISearch
 * @description: 批量处理html标签
 * @author: zjj
 * @create: 2019-09-27 20:53:10
 **/
public class BatchDeleteHtmlTag {

    private static QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

    public static void main(String[] args) throws Exception {

        String sql = null;
        String sql2 = null;
        for (int i = 101266; i < 101269; i++) {
            sql = "select title,content from article where id=?";
            Object query[] = qr.query(sql, new ArrayHandler(), i);
            System.out.println(query.length + "----" + i);
            if (query.length > 0) {
                String title = HTMLUtil.delHTMLTag(query[0].toString());
                String content = HTMLUtil.delHTMLTag(query[1].toString());
                sql2 = "update article set title = ?,content=? where id=?";
                Object params[] = {title, content, i};
                qr.update(sql2, params);
            }
        }
    }
}


