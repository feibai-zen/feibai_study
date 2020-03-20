
package com.feibai.study.demos.multithread.advanced.t07;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * 并发容器 - Queue
 */
public class Test_02 {

	static Queue<String> list = new ConcurrentLinkedQueue<>();

	static {
		for (int i = 0; i < 10000; i++) {
			list.add("String " + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						String str = list.poll();
						if (str == null) {
							break;
						}
						System.out.println(Thread.currentThread().getName() + " - " + str);
					}
				}
			}, "Thread" + i).start();
		}
	}

}
