package com.feibai.study.demos.io.commons_io.lesson1;

import java.io.File;

/**
 *  使用面向对象: 统计文件夹的大小
 *
 *
 */
public class DirCount {
	//大小
	private long len;
	//文件夹路径
	private String path;
	//文件的个数
	private int fileSize;
	//文件夹的个数
	private int dirSize;
	//源
	private File src;
	public DirCount(String path) {
		this.path = path;
		this.src = new File(path);
		count(this.src);
	}	
	
	//统计大小
	private  void count(File src) {	
		//获取大小
		if(null!=src && src.exists()) {
			if(src.isFile()) {  //大小
				len+=src.length();
				this.fileSize++;
			}else { //子孙级
				this.dirSize++;
				for(File s:src.listFiles()) {
						count(s);
				}
			}
		}
	}	
	
	public long getLen() {
		return len;
	}

	public int getFileSize() {
		return fileSize;
	}

	public int getDirSize() {
		return dirSize;
	}
	
	public static void main(String[] args) {
		DirCount dir = new DirCount("D:\\java300\\IO_study01");
		System.out.println(dir.getLen()+"-->"+dir.getFileSize()+"-->"+dir.getDirSize());
		
		DirCount dir2 = new DirCount("D:/java300/IO_study01/src");
		System.out.println(dir2.getLen()+"-->"+dir2.getFileSize()+"-->"+dir2.getDirSize());

	}	


	
	
	
}
