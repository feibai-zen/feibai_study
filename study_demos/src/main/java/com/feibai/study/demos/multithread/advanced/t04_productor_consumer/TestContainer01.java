package com.feibai.study.demos.multithread.advanced.t04_productor_consumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者
 * 
 * wait&notify
 * 
 * wait/notify都是和while配合应用的。可以避免多线程并发判断逻辑失效问题。
 */
public class TestContainer01<E> {

	private final LinkedList<E> list = new LinkedList<>();
	private final int MAX = 10;
	private int count = 0;

	public synchronized int getCount() {
		return count;
	}

	public synchronized void put(E e) {
		/**
		 * 这里为什么使用while不用if？如果使用if会导致虚假唤醒状态。也就是说if只经过一次判断，到达wait()语句
		 * 的时候会休眠并释放锁，当再次被唤醒的时候，将继续执行wait()之后的语句，而不会再去判断是否符合条件
		 */
		while (list.size() == MAX) {
			try {
				this.wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		list.add(e);
		count++;
		this.notifyAll();
	}

	public synchronized E get() {
		E e = null;
		while (list.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		e = list.removeFirst();
		count--;
		this.notifyAll();
		return e;
	}

	public static void main(String[] args) {
		final TestContainer01<String> c = new TestContainer01<>();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
						System.out.println(c.get());
					}
				}
			}, "consumer" + i).start();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 25; j++) {
						c.put("container value " + j);
					}
				}
			}, "producer" + i).start();
		}
	}

}
