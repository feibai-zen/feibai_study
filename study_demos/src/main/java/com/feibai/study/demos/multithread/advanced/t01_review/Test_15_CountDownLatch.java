package com.feibai.study.demos.multithread.advanced.t01_review;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 门闩 - CountDownLatch
 * 
 * 可以和锁混合使用，或替代锁的功能。
 * 
 * 在门闩未完全开放之前等待。当门闩完全开放后执行。
 * 
 * 避免锁的效率低下问题。
 */
public class Test_15_CountDownLatch {
	CountDownLatch latch = new CountDownLatch(5); // 在门上加5把锁

	void m1() {
		try {
			latch.await();// 等待门闩开放。
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m1() method");
	}

	void m2() {
		for (int i = 0; i < 10; i++) {
			if (latch.getCount() != 0) {
				System.out.println("latch count : " + latch.getCount());
				latch.countDown(); // 减门闩上的锁。
			}
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("m2() method : " + i);
		}
	}

	public static void main(String[] args) {
		final Test_15_CountDownLatch t = new Test_15_CountDownLatch();
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

/**
 * 应用场景1：
 * 模拟一个应用程序启动类，开始就启动N个线程，去检查N个外部服务是否正常并通知闭锁；启动类一直在闭锁上
 * 等待， 一旦验证和检查了所有外部服务，就恢复启动类执行
 */
