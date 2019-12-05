package com.feibai.study.demos.designpatterns.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 测试多线程环境下五种创建单例模式的效率。 效率测试结果：饿汉式>静态内部类>枚举单例>双重检查锁式>懒汉式
 * 
 * @author leeyuanlong
 *
 */
public class EfficientTest {

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();
		int threadNum = 10000;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNum);// 门栓锁

		for (int i = 0; i < threadNum; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {

					for (int i = 0; i < 1000000; i++) {
						Object o = SingletonEnum.INSTANCE;
					}

					countDownLatch.countDown();
				}
			}).start();
		}

		countDownLatch.await(); // main线程阻塞，直到计数器变为0，才会继续往下执行！

		long end = System.currentTimeMillis();
		System.out.println("total time: " + (end - start) + " ms.");
	}
}
