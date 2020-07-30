package com.feibai.study.demos.io.commons_io.lesson1;

import java.io.File;

/**
 * 
 *
 *
 */
public class FileDemo01 {
	/**
	 * 构建File对象
	 * @param args
	 */
	public static void main(String[] args) {
		String path ="D:/java300/IO_study01/IO.png";
		
		//1、构建File对象
		File src = new File(path);
		System.out.println(src.length());
		
		//2、构建File对象
		src = new File("D:/java300/IO_study01","IO.png");
		src = new File("D:/java300/","IO_study01/IO.png");
		System.out.println(src.length());
		
		//3、构建File对象
		src = new File(new File("D:/java300/IO_study01"),"IO.png");
		System.out.println(src.length());
	}

}
