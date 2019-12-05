package com.feibai.study.demos.demos.multithread.advanced.t02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyHomeWork {
	private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(5);
	private static final int TOTAL = 10;
	private static Container<Integer> container = new Container<Integer>(20);

	public static void main(String[] args) throws InterruptedException {

		new Thread(() -> {
			for (int i = 0; i < TOTAL; i++) {
				addElement();
			}

		}, "Thread1").start();

		new Thread(() -> {
			try {
				COUNT_DOWN_LATCH.await();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread is terminated.");

		}, "Thread2").start();

		TimeUnit.SECONDS.sleep(10);

		System.out.println(container.size());

	}

	private static void addElement() {
		if (COUNT_DOWN_LATCH.getCount() != 0) {
			COUNT_DOWN_LATCH.countDown();
		}
		container.add((int) (Math.random()) * 100);
		System.out.println("container size: " + container.size());

	}

}

class Container<E> {
	private static final Object[] EMPTY_ELEMENTDATA = {};

	volatile Object[] elements;
	int eleSize;

	public Container(int size) {
		elements = new Object[size];
	}

	public Container() {
		elements = EMPTY_ELEMENTDATA;
	}

	public void add(E e) {
		elements[eleSize++] = e;
	}

	public int size() {
		return eleSize;

	}

}