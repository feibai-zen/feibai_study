package com.feibai.study.demos.io.commons_io.lesson3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件拷贝：文件字节输入、输出流
 *
 *
 *
 */
public class CopyTxt {

	public static void main(String[] args) {
		copy("abc.txt","abc-copy.txt"); 
	}	
	public static void copy(String srcPath,String destPath) {
		//1、创建源
			File src = new File(srcPath); //源头
			File dest = new File(destPath);//目的地
			//2、选择流		
			try( BufferedReader br=new BufferedReader(new FileReader(src));
					BufferedWriter bw =new BufferedWriter( new FileWriter(dest));	) {				
				//3、操作 (逐行读取)
				String line =null;
				while((line=br.readLine())!=null) {
					bw.write(line); //逐行写出
					bw.newLine();
				}			
				bw.flush();
			}catch(FileNotFoundException e) {		
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
	}
}
