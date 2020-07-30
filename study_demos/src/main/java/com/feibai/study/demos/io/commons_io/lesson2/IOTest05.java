package com.feibai.study.demos.io.commons_io.lesson2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 四个步骤: 分段读取 文件字符输入流
 * 1、创建源
 * 2、选择流
 * 3、操作
 * 4、释放资源
 * 
 *
 *
 */
public class IOTest05 {

	public static void main(String[] args) {
		//1、创建源
		File src = new File("abc.txt");
		//2、选择流
		Reader  reader =null;
		try {
			reader =new FileReader(src);
			//3、操作 (分段读取)
			char[] flush = new char[1024]; //缓冲容器
			int len = -1; //接收长度
			while((len=reader.read(flush))!=-1) {
				//字符数组-->字符串
				String str = new String(flush,0,len);
				System.out.println(str);
			}		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//4、释放资源
			try {
				if(null!=reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
