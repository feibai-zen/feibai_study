package com.feibai.study.demos.demos.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:PermSize=8m -XX:MaxPermSize=8m
 * <p>
 * 总结：
 * 可见在jdk8中：
 * 1.字符串常量由永久代转移到堆中。
 * 2.持久代已不存在，PermSize MaxPermSize参数已移除。
 */
public class Test01_StringIntern {
  static String base = "string";

  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      String str = base + base;
      base = str;
      list.add(str.intern());
    }
  }
}
