package com.feibai.study.demos.multithread.advanced.t05_threadlocal;

/**
 * @author feibai
 */
public class ThreadLocal_demo03 {
  private static ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> 1);

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      new Thread(new MyRun()).start();
    }
  }

  public static class MyRun implements Runnable {
    public void run() {
      Integer left = tl.get();
      System.out.println(Thread.currentThread().getName() + "得到了-->" + left);
      tl.set(left - 1);
      System.out.println(Thread.currentThread().getName() + "还剩下-->" + tl.get());
    }
  }

}