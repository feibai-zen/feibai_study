package com.my.study.demos.multithread.advanced.t01_review;

import java.util.concurrent.TimeUnit;
/**
 * synchronized关键字
 * 同步方法 - 调用其他同步方法
 * 
 * 锁可重入。 同一个线程，多次调用同步代码，锁定同一个锁对象，可重入。
 */
public class Test_06_Synchronized {

	synchronized void m1() { // 锁this
		System.out.println("m1 start");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();
		System.out.println("m1 end");
	}

	synchronized void m2() { // 锁this
		System.out.println("m2 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2 end");
	}

	public static void main(String[] args) {

		new Test_06_Synchronized().m1();

	}

}
