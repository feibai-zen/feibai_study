package com.feibai.study.demos.multithread.createthread;

/**
 * 共享资源，并发（需要保证线程安全）
 *
 * @author feibai
 */
public class Web12306 implements Runnable {

  private static int ticket = 30;//使用static类属性，所有对象共享

  @Override
  public void run() {
    while (true) {
      if (ticket <= 0) {
        break;
      }
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "购买一张后，还剩余张数. cnt: " + (ticket--));
    }
  }

}
