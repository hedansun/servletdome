package com.hd.excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class XxlsBig extends XxlsAbstract {
	public void test(String path) throws Exception {
		XxlsBig howto = new XxlsBig("test2"); // 传入表
		// howto.processOneSheet("D:/test/test5.xlsx",1);
		howto.process("D:/test/test4.xlsx");
		howto.close();
	}

	public XxlsBig(String tableName) throws SQLException,
			ClassNotFoundException { // 接收一个参数 表名
		this.conn = getNew_Conn();
		this.statement = conn.createStatement();
		this.tableName = tableName;
	}

	private Connection conn = null;
	private Statement statement = null;
	private PreparedStatement newStatement = null;
	private String tableName = "temp_table";
	private boolean create = true;

	public void optRows(int sheetIndex, int curRow, List<String> rowlist)
			throws SQLException {
		if (sheetIndex == 0 && curRow == 0) {
			StringBuffer preSql = new StringBuffer("insert into test2 values(");
			// StringBuffer table = new StringBuffer("create table " +
			// tableName+ "(");
			int c = rowlist.size();
			for (int i = 0; i < c; i++) {
				preSql.append("?,");
				// table.append(rowlist.get(i));
				// table.append(" varchar2(100) ,");
			}
			// table.deleteCharAt(table.length() - 1); //删除 创建表语句的最后一个逗号
			preSql.deleteCharAt(preSql.length() - 1);
			// table.append(")");
			preSql.append(")");
			/*
			 * if (create) { statement = conn.createStatement(); try{
			 * statement.execute("drop table "+tableName); }catch(Exception e){
			 * 
			 * }finally{ System.out.println("表 "+tableName+" 删除成功"); } if
			 * (!statement.execute(table.toString())) { System.out.println("创建表
			 * "+tableName+" 成功"); // return; } else { System.out.println("创建表
			 * "+tableName+" 失败"); return; } }
			 */
			conn.setAutoCommit(false); // 默认不提交事务
			newStatement = conn.prepareStatement(preSql.toString());
			System.out.println(preSql.toString());
		} else if (curRow > 0) {
			// 一般行 不是表头 是真正的数据
			int col = rowlist.size();
			for (int i = 0; i < col; i++) {
				newStatement.setString(i + 1, rowlist.get(i).toString());
			}
			newStatement.addBatch();
			if (curRow % 1000 == 0) {
				newStatement.executeBatch();
				conn.commit();
			}
		}
	}

	private static Connection getNew_Conn() throws ClassNotFoundException,
			SQLException {
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
		return con;
	}

	public int close() {
		try {
			newStatement.executeBatch();
			conn.commit();
			System.out.println("数据写入完毕");
			this.newStatement.close();
			this.statement.close();
			this.conn.close();
			return 1;
		} catch (SQLException e) {
			return 0;
		}
	}
}