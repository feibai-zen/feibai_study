package com.feibai.study.demos.multithread.foundation.join;

/**
 * join:合并线程、线程插队
 * <p>
 * 等待子线层执行完之后主线程再往下执行。
 * join写在哪个线程中，哪个线程就被阻塞。只要子线程是活的，主线程就不停的等待。
 *
 * @author feibai
 */
public class BlockedJoinTest {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("当前线程： " + Thread.currentThread().getName() + i);
      }
    });
    thread.start();

    for (int i = 0; i < 100; i++) {
      System.out.println("当前线程： " + Thread.currentThread().getName() + i);
      if (i == 20) {
        System.out.println("当前线程： " + Thread.currentThread().getName() + i + "Blocked");
        // 让主线程等待，主线程在thread线程执行完成之后才能继续执行
        thread.join();
      }

    }
  }
}
