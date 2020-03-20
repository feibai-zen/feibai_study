package com.feibai.study.demos.multithread.foundation.notify_demos;

public class WaitTest {
	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		synchronized (t1) {
			try {
				System.out.println(Thread.currentThread().getName() + " start t1");
				t1.start();
				System.out.println(Thread.currentThread().getName() + " wait()");
				t1.wait();

				System.out.println(Thread.currentThread().getName() + " continue");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
