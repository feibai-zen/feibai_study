package com.feibai.spring.study.multithread.foundation.createthread;


/**
 * 创建线程
 * <p>
 * 实现Runable接口方式创建线程
 */
public class ThreadRunnable implements Runnable {

  private int ticket = 10;

  @Override
  public void run() {
    doPrint();
  }

  private void doPrint() {
    while (true) {
      System.out.println("线程名称：" + Thread.currentThread().getName() + ",ticket=" + ticket--);
      if (ticket <= 0) {
        break;
      }
    }
  }

}
