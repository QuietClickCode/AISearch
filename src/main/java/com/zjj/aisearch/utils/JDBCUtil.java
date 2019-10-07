package com.zjj.aisearch.utils;
import java.io.File;
import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static{
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(new File("src/mysql.properties"));
            properties.load(fis);
            url = properties.getProperty("url");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            Class.forName(driver);
        } catch (Exception e) {
            
            e.getMessage();
        }
        
    }
    
    public static Connection getConn() throws Exception{
        
        Connection connection = DriverManager.getConnection(url, user, password);
        
        return connection;
    }
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, 
            Connection connection){
        
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if(preparedStatement != null ){
                preparedStatement.close();
            }
            if(connection != null ){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}