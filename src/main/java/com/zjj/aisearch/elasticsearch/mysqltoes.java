package com.zjj.aisearch.elasticsearch;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.DB2ESImportBuilder;
import org.frameworkset.elasticsearch.client.DataStream;
import org.junit.Test;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-09 12:54:16
 **/
public class mysqltoes {
    @Test
    public void testSimpleImportBuilder(){
        DB2ESImportBuilder importBuilder = DB2ESImportBuilder.newInstance();
        try {
            //清除测试表数据
            ElasticSearchHelper.getRestClientUtil().dropIndice("article");
        }
        catch (Exception e){

        }
        //数据源相关配置，可选项，可以在外部启动数据源
        importBuilder.setDbName("test")
                .setDbDriver("com.mysql.jdbc.Driver") //数据库驱动程序，必须导入相关数据库的驱动jar包
                .setDbUrl("jdbc:mysql://localhost:3306/aisearch?useCursorFetch=true") //通过useCursorFetch=true启用mysql的游标fetch机制，否则会有严重的性能隐患，useCursorFetch必须和jdbcFetchSize参数配合使用，否则不会生效
                .setDbUser("root")
                .setDbPassword("123")
                .setValidateSQL("select 1")
                .setUsePool(false);//是否使用连接池


        //指定导入数据的sql语句，必填项，可以设置自己的提取逻辑
        importBuilder.setSql("select * from article");
        /**
         * es相关配置
         */
        importBuilder
                .setIndex("article") //必填项
                .setIndexType("article") //必填项
                .setRefreshOption(null)//可选项，null表示不实时刷新，importBuilder.setRefreshOption("refresh");表示实时刷新
                .setUseJavaName(true) //可选项,将数据库字段名称转换为java驼峰规范的名称，例如:doc_id -> docId
                .setBatchSize(5000)  //可选项,批量导入es的记录数，默认为-1，逐条处理，> 0时批量处理
                .setJdbcFetchSize(10000);//设置数据库的查询fetchsize，同时在mysql url上设置useCursorFetch=true启用mysql的游标fetch机制，否则会有严重的性能隐患，jdbcFetchSize必须和useCursorFetch参数配合使用，否则不会生效


        /**
         * 执行数据库表数据导入es操作
         */
        DataStream dataStream = importBuilder.builder();
        dataStream.execute();
    }


}
