package com.feibai.study.demos.demos.datastructure.datastructure;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {

  public static void main(String[] args) {

    List<String> cpowlist = new CopyOnWriteArrayList<>();
    cpowlist.add("a");
    cpowlist.add("b");
    System.out.println(cpowlist.toString());

  }
}
