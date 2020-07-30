package com.feibai.study.demos.io.commons_io.lesson2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * try ...with...resource
 *
 *
 */
public class FileUtils2 {

	public static void main(String[] args) {
		//文件到文件
		try {
			InputStream is = new FileInputStream("abc.txt");
			OutputStream os = new FileOutputStream("abc-copy.txt");
			copy(is,os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//文件到字节数组
		byte[] datas = null;
		try {
			InputStream is = new FileInputStream("p.png");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			copy(is,os);
			datas = os.toByteArray();
			System.out.println(datas.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//字节数组到文件
		try {
			InputStream is = new ByteArrayInputStream(datas);
			OutputStream os = new FileOutputStream("p-copy.png");
			copy(is,os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 对接输入输出流
	 * try ...with...resource
	 * @param is
	 * @param os
	 */
	public static void copy(InputStream is,OutputStream os) {		
			try(is;os) {			
				//3、操作 (分段读取)
				byte[] flush = new byte[1024]; //缓冲容器
				int len = -1; //接收长度
				while((len=is.read(flush))!=-1) {
					os.write(flush,0,len); //分段写出
				}			
				os.flush();
			}catch(FileNotFoundException e) {		
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
	}	
}
