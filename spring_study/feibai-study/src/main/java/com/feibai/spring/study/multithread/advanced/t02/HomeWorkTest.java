package com.feibai.spring.study.multithread.advanced.t02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * feibai
 *
 * @param <E>
 */
public class HomeWorkTest<E> {

  private volatile List<E> list = new ArrayList();
  CountDownLatch cdl = new CountDownLatch(5);

  public int getSize() throws InterruptedException {
    cdl.await();
    System.out.println("getSize.....");
    return list.size();
  }

  public void add(E element) throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      Thread.sleep(1000);
      cdl.countDown();
      System.out.println("add ........." + i);
      list.add(element);
    }
  }

  public static void main(String[] args) {
    HomeWorkTest<Integer> test = new HomeWorkTest();
    new Thread(() -> {
      try {
        test.add(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        test.getSize();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

}
