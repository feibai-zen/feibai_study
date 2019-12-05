
package com.feibai.study.demos.demos.multithread.advanced.t02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/**
 * CountDownLatch 门闩
 */
public class Test_03_CountDownLatch {
	public static void main(String[] args) {
		final Test_03_Container t = new Test_03_Container();
		final CountDownLatch latch = new CountDownLatch(1);

		new Thread(new Runnable() {
			@Override
			public void run() {
				if (t.size() != 5) {
					try {
						latch.await(); // 等待门闩的开放。 不是进入等待队列
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("size = 5");
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("add Object to Container " + i);
					t.add(new Object());
					if (t.size() == 5) {
						latch.countDown(); // 门闩-1
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

class Test_03_Container {
	List<Object> container = new ArrayList<>();

	public void add(Object o) {
		this.container.add(o);
	}

	public int size() {
		return this.container.size();
	}
}