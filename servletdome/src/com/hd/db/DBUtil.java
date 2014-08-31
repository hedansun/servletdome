package com.hd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection("proxool.mysql"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
