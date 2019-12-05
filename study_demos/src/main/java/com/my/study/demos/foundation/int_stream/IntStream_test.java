package com.my.study.demos.foundation.int_stream;

import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * @ClassName IntStream_test
 * @Description TODO
 * @Author leeyuanlong
 * @Date 2019/11/25 8:28 下午
 **/

public class IntStream_test {

  public static void main(String[] args) {
    test01(0, 100);
  }


  private static void test01(int start, int offset) {
    Iterator<Integer> iterator = IntStream.range(start, start + offset).boxed().iterator();

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

}


