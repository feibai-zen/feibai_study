package com.my.study.demos.multithread.foundation.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁: 锁可以延续使用 + 计数器
 * 
 * @author leeyuanlong
 *
 */
public class LockTest04 {
	ReentrantLock lock = new ReentrantLock();

	public void a() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		doSomething();
		lock.unlock();
		System.out.println(lock.getHoldCount());
	}

	// 不可重入
	public void doSomething() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		// ...................
		lock.unlock();
		System.out.println(lock.getHoldCount());
	}

	public static void main(String[] args) throws InterruptedException {
		LockTest04 test = new LockTest04();
		test.a();
		Thread.sleep(1000);
		System.out.println(test.lock.getHoldCount());
	}

}
