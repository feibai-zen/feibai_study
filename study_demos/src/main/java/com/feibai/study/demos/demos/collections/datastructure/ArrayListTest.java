package com.feibai.study.demos.demos.collections.datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 特点：1）有序  2）可为空  3）线程不安全  4）可以重复
 * 优点：访问速度快
 * 缺点： 1.底层使用数组存储，空间使用率低   2.增删速度慢  3.扩容慢
 */
public class ArrayListTest {

  public static void main(String[] args) {
    ArrayListTest instance = new ArrayListTest();
//    instance.test_02();
    instance.test_03();
  }

  /**
   * list转换成数组
   */
  public void test_01() {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      int x = Integer.valueOf(i);
      list.add(x);
    }
    Integer[] arr = new Integer[list.size()];
    list.toArray(arr);
  }

  /**
   * subList()
   */
  public void test_02() {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      int x = Integer.valueOf(i);
      list.add(x);
    }
    System.out.println(list);
    List<Integer> sublist = list.subList(0, 6);
    System.out.println(sublist);
  }

  /**
   * sort
   */
  public void test_03() {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      int x = Integer.valueOf(i);
      list.add(x);
    }
    list.sort(Comparator.naturalOrder());//正序
    System.out.println(list);
    list.sort(Comparator.reverseOrder());//逆序
    System.out.println(list);
  }
}
