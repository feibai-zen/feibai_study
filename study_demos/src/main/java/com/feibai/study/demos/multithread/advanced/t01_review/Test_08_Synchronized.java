package com.feibai.study.demos.multithread.advanced.t01_review;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字
 * <p>
 * 同步方法 - 锁与异常
 * <p>
 * 当同步方法中发生异常的时候，自动释放锁资源。不会影响其他线程的执行。
 * <p>
 * 注意，同步业务逻辑中，如果发生异常该如何处理。比如：可以在catch块中做补偿操作、或者回滚操作
 */
public class Test_08_Synchronized {
  int i = 0;

  synchronized void m() {
    System.out.println(Thread.currentThread().getName() + " - start");
    while (true) {
      i++;
      System.out.println(Thread.currentThread().getName() + " - " + i);
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (i == 5) {
        i = 1 / 0;
      }
    }
  }

  public static void main(String[] args) {
    final Test_08_Synchronized t = new Test_08_Synchronized();
    new Thread(new Runnable() {
      @Override
      public void run() {
        t.m();
      }
    }, "t1").start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        t.m();
      }
    }, "t2").start();
  }

}
