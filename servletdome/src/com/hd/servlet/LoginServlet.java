package com.hd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hd.db.DBUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends RootServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();

		conn = DBUtil.getConnection();
		try {
			String sql = "select * from t_user";
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("email"));
				System.out.println(rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}

	}

}
