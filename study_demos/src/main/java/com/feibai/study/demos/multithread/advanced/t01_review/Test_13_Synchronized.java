package com.feibai.study.demos.multithread.advanced.t01_review;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字--视频：高并发编程基础01-05
 * <p>
 * 锁对象变更问题
 * <p>
 * 同步代码一旦加锁后，会有一个临时的锁引用执行锁对象，和真实的引用无直接关联。
 * <p>
 * 在锁未释放之前，修改锁对象引用，不会影响同步代码的执行。
 */
public class Test_13_Synchronized {
  Object o = new Object();

  void m() {
    System.out.println(Thread.currentThread().getName() + " start");
    synchronized (o) {
      while (true) {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - " + o);
      }
    }
  }

  public static void main(String[] args) {
    final Test_13_Synchronized t = new Test_13_Synchronized();
    new Thread(new Runnable() {
      @Override
      public void run() {
        t.m();
      }
    }, "thread1").start();

    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        t.m();
      }
    }, "thread2");
    t.o = new Object();// 这里修改了锁定对象，不会影响线程thread1.
    thread2.start();
  }

}
