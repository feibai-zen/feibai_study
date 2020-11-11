package com.feibai.study.demos.demos.collections.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.IntStream;

public class ArrayListTrimToSize_Test {

  public static void main(String[] args) {
    ArrayList list = new ArrayList<>(10);
    Iterator<Integer> iterable = IntStream.rangeClosed(0, 10).boxed().iterator();
    while (iterable.hasNext()) {
      list.add("ma_dao" + iterable.next());
    }

    list.trimToSize();
  }

}
