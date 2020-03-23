package com.feibai.study.demos.multithread.advanced.t05_threadlocal;

/**
 * InheritableThreadLocal:继承上下文环境的数据 ，拷贝一份给子线程
 * 
 * @author feibai
 *
 */
public class ThreadLocalTest04 {
	private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {
		threadLocal.set(2);
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());

		// 线程由main线程开辟
		Thread t = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
			threadLocal.set(200);
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		});
		t.start();
		t.join();
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
	}

}
