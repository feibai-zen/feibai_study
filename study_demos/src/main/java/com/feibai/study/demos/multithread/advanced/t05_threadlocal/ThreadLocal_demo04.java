package com.feibai.study.demos.multithread.advanced.t05_threadlocal;

/**
 * ThreadLocal: 分析上下文 环境 起点
 * 1、构造器: 哪里调用 就属于哪里 找线程体
 * 2、run方法:本线程自身的
 *
 * @author feibai
 */
public class ThreadLocal_demo04 {
  private static ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> 1);

  public static void main(String[] args) {
    new Thread(new MyRun(), "thread@@1").start();
    new Thread(new MyRun(), "thread@@2").start();
  }

  public static class MyRun implements Runnable {
    public MyRun() {// main方法调用的
      System.out.println(Thread.currentThread().getName() + "-->" + "construct");
      tl.set(-100);
      System.out.println(Thread.currentThread().getName() + "-->" + tl.get());
    }

    public void run() {
      System.out.println(Thread.currentThread().getName() + "-->" + tl.get());
    }
  }

}
