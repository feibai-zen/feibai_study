package com.my.study.demos.multithread.advanced.t01_review;

public class ThreadSyncronizedTest {

	public static void main(String[] args) {
		Example example = new Example();

		Thread t1 = new Thread1(example);
		Thread t2 = new Thread2(example);

		t1.start();
		t2.start();
	}
}

class Example {
	private Object object = new Object();

	public void execute() {
		synchronized (object) {
			for (int i = 0; i < 20; ++i) {
				try {
					// Thread.sleep(500);
					Thread.sleep((long) Math.random() * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Hello: " + i);
			}
		}
	}

	public void execute2() {
		synchronized (object) {
			for (int i = 0; i < 20; ++i) {
				try {
					Thread.sleep((long) Math.random() * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("World: " + i);
			}
		}
	}

}

class Thread1 extends Thread {
	private Example example;

	public Thread1(Example example) {
		this.example = example;
	}

	@Override
	public void run() {
		example.execute();
	}

}

class Thread2 extends Thread {
	private Example example;

	public Thread2(Example example) {
		this.example = example;
	}

	@Override
	public void run() {
		example.execute2();
	}

}
