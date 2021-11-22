package com.feibai.study.demos.jvm.reference;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 软引用活不过下一次GC
 */
public class WeakReferenceTest {
  public static void main(String[] args) {

    WeakReference<byte[]> weak = new WeakReference<>(new byte[1024 * 1024 * 10]);
    System.out.println(weak.get());
    System.gc();
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(weak.get());
  }

}
