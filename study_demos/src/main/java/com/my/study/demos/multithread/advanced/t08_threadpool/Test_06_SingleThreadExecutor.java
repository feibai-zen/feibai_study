
package com.my.study.demos.multithread.advanced.t08_threadpool;

/**
 * 线程池
 * 容量为1的线程池。 顺序执行。
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test_06_SingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		System.out.println(service);

		for (int i = 0; i < 5; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " - test executor");
				}
			});
		}

	}

}
