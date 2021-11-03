package com.feibai.study.demos.jvm.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class SoftReferenceTest1 {

  public static void main(String[] args) {
    // m是强引用，new N是一个包含10M数据的对象，SoftReference对象对这个字节数组的引用是软引用
    SoftReference<N> m = new SoftReference<>(new N());
    System.out.println(m);
    System.out.println(m.get());

    System.gc();
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (Exception e) {

    }
    System.out.println("after gc->" + m.get());

    // 再分配一个数组，heap堆装不下，这时候系统会触发垃圾收集，先回收一次，如果回收之后，空
    // 间仍然不够，会把软引用指向的对象回收掉。如果内存再不够分配，就会抛出内存不足异常。
    byte[] b = new byte[1024 * 1024 * 17];
    System.out.println("after big object create->" + m.get()); // 运行结束后
  }
}

class N {
  private byte[] content;

  public N() {
    this.content = new byte[1024 * 1024];
  }

  @Override
  protected void finalize() {
    System.out.println("class N finalize() method.");
  }
}
