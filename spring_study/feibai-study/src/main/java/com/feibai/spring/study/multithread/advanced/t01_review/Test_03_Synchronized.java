
package com.feibai.spring.study.multithread.advanced.t01_review;

/**
 * synchronized关键字
 * 
 * 同步方法 - 原子性
 * 
 * 加锁的目的： 就是为了保证操作的原子性
 * 
 * 
 * 乐观锁、悲观锁是策略
 */
public class Test_03_Synchronized implements Runnable {

	private int count = 0;

	@Override
	public synchronized void run() {
		System.out.println(Thread.currentThread().getName() + " count = " + count++);
	}

	public static void main(String[] args) throws InterruptedException {
		Test_03_Synchronized t = new Test_03_Synchronized();
		for (int i = 0; i < 5; i++) {
			new Thread(t, "Thread - " + i).start();
		}
	}

}
