package com.feibai.leetcode.study.solution;

import com.feibai.leetcode.study.beans.ListNode1;

import java.util.ArrayList;
import java.util.List;

public class Lc_83_DeleteDuplicates {

  public static void main(String[] args) {

  }

  public static ListNode1 deleteDuplicates(ListNode1 head) {
    ListNode1 dummyNode = new ListNode1(-1);
    dummyNode.next = head;
    List<Integer> list = new ArrayList<>();
    ListNode1 current = dummyNode;
    while (current.next != null) {
      ListNode1 p = current.next;
      if (list.contains(p.val)) {
        current.next = p.next;
      } else {
        current = current.next;
      }
      list.add(p.val);
    }
    return dummyNode.next;
  }
}
