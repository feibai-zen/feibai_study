package com.feibai.study.demos.demos.multithread.foundation.createthread;

/**
 * 
 * 共享资源，并发（需要保证线程安全）
 * 
 * @author leeyuanlong
 *
 */
public class Web12306 implements Runnable {

	private static int ticket = 30;

	@Override
	public void run() {
		while (true) {
			if (ticket <= 0) {
				break;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "购买一张后，还剩余张数. cnt: " + (ticket--));
		}

	}

	public static void main(String[] args) {
		Web12306 runnableThread = new Web12306();
		Web12306 runnableThread1 = new Web12306();

		new Thread(runnableThread, "user01").start();
		new Thread(runnableThread1, "user02").start();
//		new Thread(runnableThread, "user03").start();

	}

}
