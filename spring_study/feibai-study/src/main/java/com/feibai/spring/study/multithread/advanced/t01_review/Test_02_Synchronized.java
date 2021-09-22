package com.feibai.spring.study.multithread.advanced.t01_review;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字
 * <p>
 * 同步方法 - static
 * <p>
 * 静态同步方法，锁的是当前类型的类对象。在本代码中就是Synchronized_Test_02.class
 * <p>
 * 注意区别类对象 、对象。一个类只有一个类对象，一个类可以有很多个对象
 */
public class Test_02_Synchronized {
  private static int staticCount = 0;

  public static synchronized void testSync4() {
    System.out.println(Thread.currentThread().getName() + " staticCount = " + staticCount++);
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void testSync5() {
    synchronized (Test_02_Synchronized.class) {
      System.out.println(Thread.currentThread().getName() + " staticCount = " + staticCount++);
    }
  }

}
