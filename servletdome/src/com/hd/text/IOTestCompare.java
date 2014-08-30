package com.hd.text;

import java.io.*;

public class IOTestCompare {
	
	public static void main(String[] args) {
		//文件名,项目组文件大小,正式库文件大小
//		readerDir(new File("D:/PRD-SOURCECODE/tp_life_cust_20140124/tp_life_cust/WebContent/WEB-INF/classes"));
		readerDir(new File("D:/PRD-SOURCECODE/tp_life_cust_20140124/tp_life_cust/WebContent/tplife"));
	}
	
	//读目录，采用递归方法
	public static void readerDir(File file){
		if(file!=null){
			if(file.isDirectory()){
				File[] fileArray=file.listFiles();
				if(fileArray!=null){
					for (int i = 0; i < fileArray.length; i++) {
						readerDir(fileArray[i]);
					}
				}
			}
			else{
				compare(file);
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
	
	//对比大小,输出
	public static void compare(File file){
//		String fileName="D:/PRD-SOURCECODE/tp_life_main20140211/WEB-INF"+(file+"").substring("D:/PRD-SOURCECODE/tp_life_cust_20140124/tp_life_cust/WebContent/WEB-INF".length(),(file+"").length());
		String fileName="D:\\PRD-SOURCECODE\\tp_life_main20140211"+(file+"").substring("D:\\PRD-SOURCECODE\\tp_life_cust_20140124\\tp_life_cust\\WebContent".length(),(file+"").length());
		File file2 = new File(fileName);
		if(file.length()!=file2.length()){
			String result = (file+","+file.length()+","+file2.length());
			write(new File("D:\\compareResultFile\\compareResult.csv"), result);
			System.out.println(file+" "+file.length()+" "+file2.length());
			String outFileName="D:\\compareResultFile\\"+file.getName().substring(0,file.getName().indexOf("."))+"_20140124.jsp";
			String outFileName2="D:\\compareResultFile\\"+file.getName().substring(0,file.getName().indexOf("."))+"_20140211.jsp";
//			String outFileName="D:\\compareResultFile\\"+file.getName().substring(0,file.getName().indexOf("."))+"_20140124.class";
//			String outFileName2="D:\\compareResultFile\\"+file.getName().substring(0,file.getName().indexOf("."))+"_20140211.class";
			copyFile(file,new File(outFileName));
			if(file2.length()>0){
				copyFile(file2,new File(outFileName2));
			}
		}
	}
	
	//写文件
	public static void write(File f,String s){
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,true)));
			bw.write(s);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//复制文件
	public static void copyFile(File f,File f2){
		try {
			FileInputStream fis = new FileInputStream(f);
			FileOutputStream fos = new FileOutputStream(f2);
			byte [] buf = new byte[1024]; 
			int len = 0;
			while((len = fis.read(buf))>-1){
				fos.write(buf, 0, len);
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
