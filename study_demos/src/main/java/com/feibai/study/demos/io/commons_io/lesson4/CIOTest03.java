package com.feibai.study.demos.io.commons_io.lesson4;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * 读取内容
 *
 *
 */
public class CIOTest03 {

	public static void main(String[] args) throws IOException {
		//读取文件
		String msg =FileUtils.readFileToString(new File("emp.txt"),"UTF-8");
		System.out.println(msg);
		byte[] datas = FileUtils.readFileToByteArray(new File("emp.txt"));
		System.out.println(datas.length);
		
		//逐行读取
		 List<String> msgs= FileUtils.readLines(new File("emp.txt"),"UTF-8");
		 for (String string : msgs) {
			System.out.println(string);
		}
		LineIterator it =FileUtils.lineIterator(new File("emp.txt"),"UTF-8");
		while(it.hasNext()) {
			System.out.println(it.nextLine());
		}
		
	}

}
