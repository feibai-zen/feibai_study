package com.feibai.study.demos.multithread.advanced.t01_review;

/**
 * volatile用于保证数据的同步，也就是可见性
 * 
 * @author feibai
 *
 */
public class VolatileTest {
	private /* volatile */ static int num = 0;// 有没有volatile修饰，差异

	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> {
			while (num == 0) { // 此处不编写代码，造成cpu一直处在忙碌状态，没有时间重新获取主存中num的最新值

			}
		}).start();

		Thread.sleep(1000);
		num = 1;
	}

}
