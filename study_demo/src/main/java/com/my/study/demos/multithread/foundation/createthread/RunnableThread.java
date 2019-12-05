package com.my.study.demos.multithread.foundation.createthread;

/**
 * 
 * 继承Runnable 推荐的使用方式，避免单继承的局限性 方便共享资源
 * 
 * @author leeyuanlong
 *
 */
public class RunnableThread implements Runnable {

	public static void main(String[] args) {
		RunnableThread runnableThread = new RunnableThread();

//		Thread proxyThread = new Thread(runnableThread, "testRunnable");
//		proxyThread.start();

		new Thread(new RunnableThread(), "testRunnable").start();// 匿名线程对象，Java中，如果一个对象只使用一次，可以使用匿名对象
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 100; i++) {
			System.out.println(i);
		}
	}

}
