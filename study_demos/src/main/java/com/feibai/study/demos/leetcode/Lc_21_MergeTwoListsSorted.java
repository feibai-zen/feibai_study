package com.feibai.study.demos.leetcode;

import com.feibai.study.demos.leetcode.beans.ListNode;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Lc_21_MergeTwoListsSorted {

  public static void main(String[] args) {

  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode tmp = dummyHead;
    while (l1 != null || l2 != null) {
      int val = 0;
      int val_1 = Integer.MAX_VALUE;
      int val_2 = Integer.MAX_VALUE;
      if (l1 != null) {
        val_1 = l1.val;
      }
      if (l2 != null) {
        val_2 = l2.val;
      }

      if (val_1 <= val_2) {
        val = l1.val;
        l1 = l1.next;
      } else {
        val = l2.val;
        l2 = l2.next;
      }

      ListNode newNode = new ListNode(val);
      tmp.next = newNode;
      tmp = newNode;
    }

    return dummyHead.next;
  }


  public ListNode mergeTwoListsOptimize(ListNode l1, ListNode l2) {
    // maintain an unchanging reference to node ahead of the return node.
    ListNode prehead = new ListNode(-1);

    ListNode prev = prehead;
    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        prev.next = l1;
        l1 = l1.next;
      } else {
        prev.next = l2;
        l2 = l2.next;
      }
      prev = prev.next;
    }

    // exactly one of l1 and l2 can be non-null at this point, so connect
    // the non-null list to the end of the merged list.
    prev.next = l1 == null ? l2 : l1;

    return prehead.next;
  }


}
