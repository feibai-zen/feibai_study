package com.feibai.leetcode.study.solution;

import com.feibai.leetcode.study.beans.ListNode1;

public class Lc_203_RemoveElements {

  public static void main(String[] args) {
    /**
     * [1,2,6,3,4,5,6]
     */
    ListNode1 head = new ListNode1(1);
    ListNode1 node1 = new ListNode1(2);
    ListNode1 node2 = new ListNode1(6);
    ListNode1 node3 = new ListNode1(3);
    ListNode1 node4 = new ListNode1(4);
    ListNode1 node5 = new ListNode1(5);
    ListNode1 node6 = new ListNode1(6, null);
    head.next = node1;
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    node5.next = node6;

    removeElements(head, 6);
    printLink(head);
  }

  public static ListNode1 removeElements(ListNode1 head, int val) {
    ListNode1 dummyHead = new ListNode1(0);
    dummyHead.next = head;
    ListNode1 current = dummyHead;
    while (current.next != null) {
      ListNode1 p = current.next;
      if (p != null && p.val == val) {
        //执行删除
        current.next = p.next;
      } else {//这里有点疑惑，为什么需要else呢？
        current = current.next;
      }
    }
    return dummyHead.next;
  }

  public static void printLink(ListNode1 head) {
    ListNode1 current = head;
    while (current != null) {
      System.out.println(current.val);
      current = current.next;
    }
  }
}
