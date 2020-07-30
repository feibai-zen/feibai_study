package com.feibai.study.demos.io.commons_io.lesson1;

import java.io.File;

/**
 * 
 *
 *
 */
public class PathDemo01 {
	/**
	 *  \  /  名称分隔符  separator
	 * @param args
	 */
	public static void main(String[] args) {
		String path ="D:\\java300\\IO_study01\\IO.png";
		System.out.println(File.separatorChar);		
		//建议
		//1、/
		path = "D:/java300/IO_study01/IO.png";
		System.out.println(path);
		//2、常量拼接
		path ="D:"+File.separator+"java300"+File.separator+"IO_study01"+File.separator+"IO.png";
		System.out.println(path);
	}

}
