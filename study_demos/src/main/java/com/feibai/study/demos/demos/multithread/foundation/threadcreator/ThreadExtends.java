package com.feibai.study.demos.demos.multithread.foundation.threadcreator;

public class ThreadExtends extends Thread {
	private int ticket = 10;

	public ThreadExtends(String name) {
		super(name);
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			System.out.println("线程名称：" + Thread.currentThread().getName() + ",ticket=" + ticket--);
			if (this.ticket == 0) {
				break;
			}
		}
	}

}
