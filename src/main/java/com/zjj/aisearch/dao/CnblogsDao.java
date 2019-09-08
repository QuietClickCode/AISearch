package com.zjj.aisearch.dao;

import com.zjj.aisearch.model.Cnblogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CnblogsDao {

	public int add(Connection con, Cnblogs cnblogs) throws SQLException {
		String sql = "insert into t_cnblogs(title,context) values (?,?)";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, cnblogs.getTitle());
		pstm.setString(2, cnblogs.getContext());
	return pstm.executeUpdate();
	}
}