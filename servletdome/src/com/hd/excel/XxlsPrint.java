package com.hd.excel;

import java.sql.SQLException;
import java.util.List;

public class XxlsPrint extends XxlsAbstract {
	@Override
	public void optRows(int sheetIndex, int curRow, List<String> rowlist)
			throws SQLException {
		for (int i = 0; i < rowlist.size(); i++) {
			System.out.print("'"+rowlist.get(i)+"',");
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		XxlsPrint howto = new XxlsPrint();
		//howto.processOneSheet("D:/workspace2/5000003.xlsx", 1);
		howto.process("D:/crmtest/cls_upload_1W_1.xlsx");
	}
}