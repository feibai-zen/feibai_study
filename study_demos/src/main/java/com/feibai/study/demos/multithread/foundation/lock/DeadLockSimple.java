package com.feibai.study.demos.multithread.foundation.lock;

import java.util.concurrent.TimeUnit;

public class DeadLockSimple {

  private static final String A = "A";
  private static final String B = "B";

  public static void main(String[] args) {

    new DeadLockSimple().deadlock();
  }

  // jdk8 简化 lambda表达式
  private void deadlock() {
    new Thread(() -> {
      synchronized (A) {
        System.out.println("" + Thread.currentThread().getName() + "-" + "持有A");
        try {
          TimeUnit.MILLISECONDS.sleep(6000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (B) {
          System.out.println("" + Thread.currentThread().getName() + "-" + "持有B");

        }
      }

    }

    ).start();

    new Thread(() -> {
      synchronized (B) {
        System.out.println("" + Thread.currentThread().getName() + "-" + "持有B");
        try {
          TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (A) {
          try {
            TimeUnit.MILLISECONDS.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("" + Thread.currentThread().getName() + "-" + "持有A");

        }
      }

    }

    ).start();

  }
}
