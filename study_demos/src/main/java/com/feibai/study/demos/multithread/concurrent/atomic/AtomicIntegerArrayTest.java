package com.feibai.study.demos.multithread.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {

  private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

  public static void main(final String[] arguments) throws InterruptedException {
    for (int i = 0; i < atomicIntegerArray.length(); i++) {
      atomicIntegerArray.set(i, 1);
    }

    Thread t1 = new Thread(new Increment());
    Thread t2 = new Thread(new Compare());
    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Values: ");
    for (int i = 0; i < atomicIntegerArray.length(); i++) {
      System.out.print(atomicIntegerArray.get(i) + " ");
    }
  }

  static class Increment implements Runnable {
    public void run() {
      for (int i = 0; i < atomicIntegerArray.length(); i++) {
        int add = atomicIntegerArray.incrementAndGet(i);
        System.out.println("Thread " + Thread.currentThread().getId()
            + ", index " + i + ", value: " + add);
      }
    }
  }

  static class Compare implements Runnable {
    public void run() {
      for (int i = 0; i < atomicIntegerArray.length(); i++) {
        boolean swapped = atomicIntegerArray.compareAndSet(i, 2, 3);
        if (swapped) {
          System.out.println("Thread " + Thread.currentThread().getId()
              + ", index " + i + ", value: 3");
        }
      }
    }
  }

}
