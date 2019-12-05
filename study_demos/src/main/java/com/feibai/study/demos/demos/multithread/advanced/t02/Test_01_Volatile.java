package com.feibai.study.demos.demos.multithread.advanced.t02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * volatile
 */

/**
 * 练习： 自定义容器，提供新增元素（add）和获取元素数量（size）方法。
 * 启动两个线程。线程1向容器中新增10个数据。线程2监听容器元素数量，当容器元素数量为5时，线程2输出信息并终止。
 * 
 * @author leeyuanlong
 *
 * @Time 2019年8月21日
 *
 */

public class Test_01_Volatile {
	public static void main(String[] args) {
		final Test_01_Container t = new Test_01_Container();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("add Object to Container " + i);
					t.add(new Object());
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (t.size() == 5) {
						System.out.println("size = 5");
						break;
					}
				}
			}
		}).start();
	}
}

class Test_01_Container {
	volatile List<Object> container = new ArrayList<>();

	public void add(Object o) {
		this.container.add(o);
	}

	public int size() {
		return this.container.size();
	}
}