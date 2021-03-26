package com.feibai.leetcode.study.solution;

import com.feibai.leetcode.study.beans.ListNode;

public class Lc_1669_MergeInBetween {

  public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    ListNode aIndexNode = null;
    ListNode bIndexNode = null;
    ListNode dummyNode = new ListNode(-1);
    int i = 0;
    dummyNode.next = list1;
    ListNode current = dummyNode;
    while (current.next != null) {
      if (i == a) {
        aIndexNode = current;
      }
      if (i == b) {
        bIndexNode = current.next.next;
      }
      if (i > a && i > b) {
        break;
      }
      i++;
      current = current.next;
    }

    aIndexNode.next = list2;
    ListNode dummyNode2 = new ListNode(-1);
    dummyNode2.next = list2;
    ListNode current_l2 = dummyNode2;
    while (current_l2.next != null) {
      current_l2 = current_l2.next;
    }
    current_l2.next = bIndexNode;
    return list1;
  }
}
