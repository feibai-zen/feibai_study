package com.my.study.demos.multithread.advanced.t03_reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 */
public class Test_04_ReentrantLock {

	public static void main(String[] args) {
		TestReentrantlock t = new TestReentrantlock();
		// TestSync t = new TestSync();
		Thread t1 = new Thread(t, "Thread1");
		Thread t2 = new Thread(t, "Thread2");
		t1.start();
		t2.start();
	}
}

class TestReentrantlock extends Thread {
	// 定义一个公平锁
	private static ReentrantLock lock = new ReentrantLock(true);// true定义公平锁，公平锁会记录等待时长

	public void run() {
		for (int i = 0; i < 5; i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + " get lock");
			} finally {
				lock.unlock();
			}
		}
	}

}

class TestSync extends Thread {
	public void run() {
		for (int i = 0; i < 5; i++) {
			synchronized (this) {
				System.out.println(Thread.currentThread().getName() + " get lock in TestSync");
			}
		}
	}
}
