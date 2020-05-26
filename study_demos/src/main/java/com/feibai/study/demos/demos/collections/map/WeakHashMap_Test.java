package com.feibai.study.demos.demos.collections.map;

import java.util.WeakHashMap;

public class WeakHashMap_Test {

  public static void main(String[] args)throws Exception {
    WeakHashMap cache = new WeakHashMap();
    for (int i = 0; i < 300; i++) {
      cache.put(i, Integer.valueOf(i));
    }

    while (true) {
      System.out.println(cache.size());
    }

  }

}
