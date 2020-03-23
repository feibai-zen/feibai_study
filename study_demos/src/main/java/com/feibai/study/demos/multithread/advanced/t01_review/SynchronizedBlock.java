package com.feibai.study.demos.multithread.advanced.t01_review;

import java.util.ArrayList;
import java.util.List;
/**
 * 操作非线程安全容器
 * @author feibai
 */
public class SynchronizedBlock {

	public static void main(String[] args) throws InterruptedException {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			new Thread(() -> {
				// 同步块
				synchronized (list) {
					list.add(Thread.currentThread().getName());
				}
			}).start();
		}
		Thread.sleep(10000);
		System.out.println(list.size());
	}
}
