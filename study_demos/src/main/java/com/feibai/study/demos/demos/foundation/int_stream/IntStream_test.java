package com.feibai.study.demos.demos.foundation.int_stream;

import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * @author feibai
 * @ClassName IntStream_test
 * @Description TODO
 * @Date 2019/11/25 8:28 下午
 **/

public class IntStream_test {

  public static void main(String[] args) {
    test01(0, 100);//0~99
    test02(0, 100);//0~100
  }

  private static void test01(int start, int offset) {
    Iterator<Integer> iterator = IntStream.range(start, start + offset).boxed().iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  private static void test02(int start, int offset) {
    Iterator<Integer> iterator = IntStream.rangeClosed(start, start + offset).boxed().iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}