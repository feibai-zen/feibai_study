package com.feibai.spring.study.multithread.advanced.t01_review;

/**
 * synchronized关键字
 * 
 * 常量问题
 * 
 * 在定义同步代码块时，不要使用常量对象作为锁对象。
 */

public class Test_14_Synchronized {
	String s1 = "hello";
//	String s2 = "hello";
	String s2 = new String("hello"); // new关键字，一定是在堆中创建一个新的对象。

	// 包装类-128~127引用的是常量池对象
	Integer i1 = 1;
	Integer i2 = 1;

	void m1() {
		synchronized (i1) {
			System.out.println("m1()");
			while (true) {

			}
		}
	}

	void m2() {
		synchronized (i2) {
			System.out.println("m2()");
			while (true) {

			}
		}
	}

	public static void main(String[] args) {
		final Test_14_Synchronized t = new Test_14_Synchronized();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m1();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		}).start();
	}

}
