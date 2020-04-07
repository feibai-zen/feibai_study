package com.feibai.study.demos.demos.collections.datastructure;


import java.util.LinkedList;
import java.util.List;

/**
 * 底层使用双向链表实现，非连续有空间，空间利用率高
 * <p>
 * 双向链表：查询快，可以从头节点开始找，也可以从尾节点开始找
 * <p>
 * 查询慢，增删快。非线程安全、有序、可以重复、可为null
 */
public class LinkedListTest {

  public static void main(String[] args) {
    LinkedListTest instance = new LinkedListTest();
    instance.test_01();
  }

  public void test_01() {
    List<Integer> linkedList = new LinkedList<>();
    System.out.println(linkedList.lastIndexOf(1));

  }

  public void test_02() {

  }
}
