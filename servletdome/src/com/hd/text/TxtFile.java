package com.hd.text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TxtFile {

	public static void main(String[] args) {
		File file = new File("d:\\test.csv");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			//OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
			String name="test";
			Long idcard=430407198906074510L;
			Long telephone=15221491640L;
			String city="上海";
			//for(int i=0;i<1000001;i++){
			for(int i=0;i<1000001;i++){
				idcard++;
				telephone++;
				bw.write(name+","+idcard+","+telephone+","+city);
				bw.newLine();
				//osw.write(name+","+idcard+","+telephone+","+city);
			}
			bw.close();
			//osw.close();
			System.out.println("ok");
			String s="";
			while((s=br.readLine())!=null){
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
