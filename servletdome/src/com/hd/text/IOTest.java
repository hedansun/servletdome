package com.hd.text;

import java.io.*;

public class IOTest {
	
	public static void main(String[] args) {
		System.out.println("生产环境如下：");
		readerDir(new File("D:"+
				File.separator+"workspace" +
				File.separator+"old_tp_life_cust" +
				File.separator+"java" +
				File.separator+"com"));
		System.out.println("最新代码如下：");
		readerDir(new File("D:"+
				File.separator+"workspace" +
				File.separator+"tp_life_cust" +
				File.separator+"java" +
				File.separator+"com"));
	}
	
	//读目录，采用递归方法
	public static void readerDir(File flie){
		if(flie!=null){
			if(flie.isDirectory()){
				File[] fileArray=flie.listFiles();
				if(fileArray!=null){
					for (int i = 0; i < fileArray.length; i++) {
						readerDir(fileArray[i]);
					}
				}
			}
			else{
				//处理每个文件,比较次数
				int count1=findString(flie,"DBUtil.getConnection()");
				int count2=findString(flie,"DBUtil.close(");
				if(count1>count2){
					System.out.println(count1+":"+count2+" "+flie.getName());
				}
			}
		}
	}
	
	//读文件，找字符串。返回次数
	public static int findString(File file,String s){
		int i = 0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String str = "";
			while((str=br.readLine())!=null){
				if(str.indexOf(s)>-1){
					i++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
