package com.zjj.aisearch.dao;

import com.zjj.aisearch.model.CsdnBlog;

import java.sql.*;

public class CsdnBlogDao {
 
	private Connection conn = null;
	private Statement stmt = null;
 
	public CsdnBlogDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/aisearch?"
					+ "user=root&password=123&useUnicode=true&characterEncoding=UTF8";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
	}
 
	public int add(CsdnBlog csdnBlog) {
		try {
			String sql = "INSERT INTO `test`.`csdnblog` (`keyes`, `titles`, `content` , `dates`, `tags`, `category`, `views`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, csdnBlog.getKey());
			ps.setString(2, csdnBlog.getTitle());
			ps.setString(3,csdnBlog.getContent());
			ps.setString(4, csdnBlog.getDate());
			ps.setString(5, csdnBlog.getTags());
			ps.setString(6, csdnBlog.getCategory());
			ps.setInt(7, csdnBlog.getView());
			ps.setInt(8, csdnBlog.getComments());
			ps.setInt(9, csdnBlog.getCopyright());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
 
}