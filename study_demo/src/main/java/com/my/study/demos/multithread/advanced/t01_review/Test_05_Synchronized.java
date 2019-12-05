
package com.my.study.demos.multithread.advanced.t01_review;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字 同步方法 - 多方法调用原子性问题（业务）
 * 
 * 同步方法只能保证当前方法的原子性，不能保证多个业务方法之间的互相访问的原子性。
 * 
 * 注意在商业开发中，多方法要求结果访问原子操作，需要多个方法都加锁，且锁定统一个资源。
 * 
 * 一般来说，商业项目中，不考虑业务逻辑上的脏读问题。
 */
public class Test_05_Synchronized {
	private double d = 0.0;

	public synchronized void set_m1(double d) {
		try {
			// 相当于复杂的业务逻辑代码。
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.d = d;
	}

	public double get_m2() {
		return this.d;
	}

	public static void main(String[] args) {
		final Test_05_Synchronized t = new Test_05_Synchronized();

		new Thread(new Runnable() {
			@Override
			public void run() {
				t.set_m1(100);
			}
		}).start();
		System.out.println(t.get_m2());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.get_m2());
	}

}
