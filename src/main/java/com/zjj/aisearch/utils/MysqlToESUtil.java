package com.zjj.aisearch.utils;

import com.zjj.aisearch.model.FullTextFile;
import com.zjj.aisearch.pojo.dto.FullTextDTO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @program: AISearch
 * @description: mysql到索引库
 * @author: zjj
 * @create: 2020-01-06 16:05:55
 **/
public class MysqlToESUtil {
    private static QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
    public static void main(String[] args) throws SQLException {
        FullTextFile fullTextFile = new FullTextFile();
        FullTextDTO fullTextDTO = new FullTextDTO();
        String sql = null;
        String sql2 = null;
            sql = "select * from ai_file";
            List<Object[]> query = qr.query(sql, new ArrayListHandler());
        for(Object[] qiang:query){
            fullTextFile.setId((Integer) qiang[0]);
            fullTextFile.setCreatetime(DateTimeUtil.dateToStr((Date) qiang[1]));
            fullTextFile.setFileType((String) qiang[2]);
            fullTextFile.setFileSize((Long) qiang[3]);
            fullTextFile.setFileContent((String) qiang[4]);
            fullTextFile.setFilePath((String) qiang[5]);
            fullTextFile.setCreateuser((String) qiang[6]);
            fullTextFile.setFileName((String) qiang[7]);
            System.out.println(fullTextFile);
fullTextDTO.setId(fullTextFile.getId());
fullTextDTO.setFileType(fullTextFile.getFileType());
fullTextDTO.setFilePath(fullTextFile.getFilePath());
fullTextDTO.setFileName(fullTextFile.getFileName());
fullTextDTO.setFileContent(fullTextFile.getFileContent());
fullTextDTO.setCreateuser(fullTextFile.getCreateuser());
fullTextDTO.setCreatetime(fullTextFile.getCreatetime());


        }

    }
}
