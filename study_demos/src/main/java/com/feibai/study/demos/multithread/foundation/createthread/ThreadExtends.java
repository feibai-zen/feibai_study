package com.feibai.study.demos.multithread.foundation.createthread;


/**
 * 创建线程
 * <p>
 * 使用继承的方式创建线程
 */
public class ThreadExtends extends Thread {
  private int ticket = 10;

  public ThreadExtends(String name) {
    super(name);
  }

  @Override
  public void run() {
    super.run();
    while (true) {
      System.out.println("线程名称：" + Thread.currentThread().getName() + ",ticket=" + ticket--);
      if (this.ticket == 0) {
        break;
      }
    }
  }

}
