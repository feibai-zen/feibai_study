package com.feibai.study.demos.io.commons_io.lesson1;

import java.io.UnsupportedEncodingException;

/**
 * 解码: 字节->字符串
 *
 *
 */
public class ContentDecode {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String msg ="性命生命使命a";
		//编码: 字节数组
		byte[] datas = msg.getBytes();  //默认使用工程的字符集
		
		//解码: 字符串 String​(byte[] bytes, int offset, int length, String charsetName)
		msg = new String(datas,0,datas.length,"utf8");
		System.out.println(msg);
		
		
		//乱码: 
		//1)、字节数不够
		msg = new String(datas,0,datas.length-2,"utf8");
		System.out.println(msg);
		msg = new String(datas,0,datas.length-1,"utf8");
		System.out.println(msg);
		
		//2)、字符集不统一
		msg = new String(datas,0,datas.length-1,"gbk");
		System.out.println(msg);
		
	}

}
