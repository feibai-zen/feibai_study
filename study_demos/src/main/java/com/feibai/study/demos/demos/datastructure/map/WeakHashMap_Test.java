package com.feibai.study.demos.demos.datastructure.map;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMap_Test {

  public static void main(String[] args)throws Exception {
    WeakHashMap cache = new WeakHashMap();
    for (int i = 0; i < 300; i++) {
      cache.put(i, Integer.valueOf(i));
    }

    while (true) {
      byte[] alloc = new byte[1024*1024*100];
      TimeUnit.MILLISECONDS.sleep(100);
      System.out.println(cache.size());
    }

  }

}
