package com.feibai.study.demos.jvm.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 存在多个软引用，遇到内存不够分配时
 *
 * -Xmx30m
 */
public class SoftReferenceTest2 {

  public static void main(String[] args) throws InterruptedException {
    // m是强引用，new byte[1024 * 1024 * 10]是一个字节数组，SoftReference对象对这个字节数组的引用是软引用
    SoftReference<P> m = new SoftReference<>(new P());
    System.out.println(m.get());

    System.gc();
    TimeUnit.SECONDS.sleep(5);
    System.out.println("after gc->" + m.get());

    // 再分配一个数组，heap堆装不下，这时候系统会触发垃圾收集，先回收一次，如果回收之后，空
    // 间仍然不够，会把软引用指向的对象回收掉。如果内存再不够分配，就会抛出内存不足异常。
    SoftReference<P> n = new SoftReference<>(new P());
    System.out.println("after big object create->" + n.get()); // 运行结束后

    byte[] byteArr = new byte[1024 * 1024 * 6];
    System.out.println(m.get());
    System.out.println(n.get());
  }
}

class P {
  private byte[] content;

  public P() {
    this.content = new byte[1024 * 1024 * 10];
  }

  @Override
  protected void finalize() {
    System.out.println("class N finalize() method.");
  }
}
