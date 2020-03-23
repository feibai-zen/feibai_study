package com.feibai.study.demos.demos.foundation.copyvalue;

/**
 * 由于String类是final修饰的，不可变，它会在内存中在开辟一块新空间。
 * 
 * @author feibai
 *
 * @Time 2019年8月11日
 *
 */
public class Test {
	public static void change(String s) {
		s = "world";
	}

	public static void main(String[] args) {
		String s = new String("hello");
		System.out.println(s);
		change(s);
		System.out.println(s);
	}
}