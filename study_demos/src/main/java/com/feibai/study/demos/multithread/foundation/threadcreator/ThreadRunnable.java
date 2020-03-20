package com.feibai.study.demos.multithread.foundation.threadcreator;

public class ThreadRunnable implements Runnable {

	private int ticket = 10;

	@Override
	public void run() {
		doPrint();
	}

	private void doPrint() {
		while (true) {
			System.out.println("线程名称：" + Thread.currentThread().getName() + ",ticket=" + ticket--);
			if (ticket <= 0) {
				break;
			}
		}
	}

}
