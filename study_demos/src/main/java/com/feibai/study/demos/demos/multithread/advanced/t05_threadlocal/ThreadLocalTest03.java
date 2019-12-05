package com.feibai.study.demos.demos.multithread.advanced.t05_threadlocal;

/**
 * ThreadLocal:分析上下文 环境 起点 1、构造器: 哪里调用 就属于哪里 找线程体 2、run方法:本线程自身的
 * 
 * @author leeyuanlong
 *
 */
public class ThreadLocalTest03 {
	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

	public static void main(String[] args) {
		new Thread(new MyRun()).start();
		new Thread(new MyRun()).start();
	}

	public static class MyRun implements Runnable {
		public MyRun() {
			threadLocal.set(-100);
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
			// new Thread(new MyRunxxx()).start();
		}
	}

}
