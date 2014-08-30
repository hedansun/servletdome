package com.hd.text;

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;

public class IOTest2 {
	
	public static void main(String[] args){
		
	}
	
	//创建目录，创建文件
	public static void create(){
		File f = new File("d:/io/");
		if(!f.exists()){
			f.mkdir();
		}
		if(f.exists()){
			try {
				f = new File("d:/io/iotest2.txt");
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//写文件
	public static void write(File f,String s){
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,true),"utf8"));
			bw.write(s);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//读文件
	public static void reader(File f){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf8"));
			String s ="";
			while((s=br.readLine())!=null){
				System.out.println(s);
			}
			br.close();
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
