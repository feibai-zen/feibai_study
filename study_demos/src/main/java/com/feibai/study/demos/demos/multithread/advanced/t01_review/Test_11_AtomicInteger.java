package com.feibai.study.demos.demos.multithread.advanced.t01_review;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicXxx 同步类型
 * 
 * 原子操作类型。 类中的每个方法都是原子操作。可以保证线程安全。
 */
public class Test_11_AtomicInteger {
	AtomicInteger count = new AtomicInteger(0);

	void m() {
		for (int i = 0; i < 10000; i++) {
			/* if(count.get() < 1000) */
			count.incrementAndGet();// CAS实现的原子操作
//			count.incrementAndGet();//++stock;
//			count.getAndIncrement();//stock++;
//			count.getAndDecrement();//stock--
		}
	}

	public static void main(String[] args) {
		final Test_11_AtomicInteger t = new Test_11_AtomicInteger();
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Runnable() {
				@Override
				public void run() {
					t.m();
				}
			}));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();// 其他线程都执行完成后，main线程继续执行
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(t.count.intValue());
	}
}
