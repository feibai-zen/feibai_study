package com.feibai.study.demos.multithread.foundation.join;

/**
 * join:合并线程、线程插队
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
          // TODO Auto-generated catch block
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
        thread.join();// 等待
      }

    }
  }
}
