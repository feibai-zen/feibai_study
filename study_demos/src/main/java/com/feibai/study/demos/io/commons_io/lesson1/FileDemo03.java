package com.feibai.study.demos.io.commons_io.lesson1;

import java.io.File;

/**
 * 名称或路径
 * getName()   : 名称
 * getPath()    : 相对、绝对
	getAbsolutePath() :绝对
	getParent(): 上路径 返回null

 *
 *
 */
public class FileDemo03 {

	public static void main(String[] args) {
		File src = new File("IO_study01/IO.png");
		
		//基本信息
		System.out.println("名称:"+src.getName());
		System.out.println("路径:"+src.getPath());
		System.out.println("绝对路径:"+src.getAbsolutePath());
		System.out.println("父路径:"+src.getParent());
		System.out.println("父对象:"+src.getParentFile().getName());
	}

}
