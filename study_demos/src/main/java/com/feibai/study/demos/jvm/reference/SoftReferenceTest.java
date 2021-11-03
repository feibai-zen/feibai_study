package com.feibai.study.demos.jvm.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 软引用
 * <p>
 * 测试软引用
 *
 * 运行时设置-Xmx30m
 */
public class SoftReferenceTest {

  public static void main(String[] args) throws InterruptedException {
    // m是强引用，new byte[1024 * 1024 * 10]是一个字节数组，SoftReference对象对这个字节数组的引用是软引用
    SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
    System.out.println(m);
    System.out.println(m.get());

    System.gc();
    TimeUnit.SECONDS.sleep(5);
    System.out.println("after gc->" + m.get());

    // 再分配一个数组，heap堆装不下，这时候系统会触发垃圾收集，先回收一次，如果回收之后，空
    // 间仍然不够，会把软引用指向的对象回收掉。如果内存再不够分配，就会抛出内存不足异常。
    byte[] b = new byte[1024 * 1024 * 12];
    System.out.println("after big object create->" + m.get()); // 运行结束后
  }
}
