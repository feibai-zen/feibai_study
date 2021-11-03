package com.feibai.study.demos.multithread.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {
  public static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater =
      AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "count");

  private volatile int count = 100;

  public int getCount() {
    return count;
  }

  private static AtomicIntegerFieldUpdaterTest concurrencyTest = new AtomicIntegerFieldUpdaterTest();

  public static void main(String[] args) throws InterruptedException {
    if (updater.compareAndSet(concurrencyTest, 100, 120)) {
      System.out.println("update success " + concurrencyTest.getCount());
    }

    if (updater.compareAndSet(concurrencyTest, 100, 130)) {
      System.out.println("update success " + concurrencyTest.getCount());
    } else {
      System.out.println("update fail " + concurrencyTest.getCount());
    }

  }
}
