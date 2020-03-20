package com.feibai.study.demos.multithread.foundation.createthread;

/**
 * 继承Runnable，实现run方法。 推荐的使用方式，避免单继承的局限性 方便共享资源
 *
 * @author feibai
 */
public class CreateThreadByImplementRunnable implements Runnable {

  public static void main(String[] args) {
    CreateThreadByImplementRunnable runnableThread = new CreateThreadByImplementRunnable();

    //Thread proxyThread = new Thread(runnableThread, "testRunnable");
    //proxyThread.start();

    // 匿名线程对象，Java中，如果一个对象只使用一次，可以使用匿名对象
    new Thread(new CreateThreadByImplementRunnable(), "testRunnable").start();
  }

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.println(i);
    }
  }

}
