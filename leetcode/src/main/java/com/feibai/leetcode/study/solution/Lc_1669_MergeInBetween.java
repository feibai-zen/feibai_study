package com.feibai.leetcode.study.solution;

import com.feibai.leetcode.study.beans.ListNode;

public class Lc_1669_MergeInBetween {

  public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    ListNode aIndexNode = null;
    ListNode bIndexNode = null;
    ListNode dummyNode = new ListNode(-1);
    dummyNode.next = list1;
    int i = 1;
    ListNode current = dummyNode;
    while (current.next != null) {
      ListNode p = current.next;
      if (i == a) {
        aIndexNode = p;
      }
      if (i == b) {
        bIndexNode = p.next;
      }
      if (i > a && i > b) {
        break;
      }
      i++;
      current = current.next;
    }

    ListNode l2LastNode = null;

    return dummyNode.next;
  }
}
