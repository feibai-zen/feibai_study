package com.feibai.study.demos.jvm.gc;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 * <p>
 * <p>
 * 通过反射获取Unsafe实例进行内存分配（Unsafe类的getUnsafe()方法限制了只有引导类加载器才会返回实例，也就是设计者希望只有rt.jar中的类才能使用Unsafe的功能）。
 */
public class DirectMemoryOOM {
  private static final int _1MB = 1024 * 1024;

  public static void main(String[] args) throws IllegalAccessException {
    Field unsafeField = Unsafe.class.getDeclaredFields()[0];
    unsafeField.setAccessible(true);
    Unsafe unsafe = (Unsafe) unsafeField.get(null);
    while (true) {
      System.out.println("allocate memory.");
      unsafe.allocateMemory(_1MB);
    }

  }
}
