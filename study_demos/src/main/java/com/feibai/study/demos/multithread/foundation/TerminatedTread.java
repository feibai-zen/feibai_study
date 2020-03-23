package com.feibai.study.demos.multithread.foundation;

/**
 * 通过设置标志位终止线程
 *
 * @author feibai
 */
public class  TerminatedTread implements Runnable {

  private boolean flag = true;

  public static void main(String[] args) {
    TerminatedTread thread = new TerminatedTread();
    new Thread(thread).start();
    try {
      Thread.sleep(5000);
      thread.terminateThread();
      Thread.activeCount();//存活线程数量
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void run() {
    while (flag) {
      try {
        Thread.sleep(200);
        System.out.println("Thread is running...");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Thread is terminated...");
  }

  public void terminateThread() {
    flag = false;
  }

}
