package com.feibai.study.demos.leetcode;

import com.feibai.study.demos.leetcode.beans.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 */
public class Lc_19_RemoveNthNodeFromEndOfList {
  public static void main(String[] args) {
    //[1,2,3,4,5]
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || n < 1) {//不删除元素
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode lastN = dummy;
    ListNode tmp = dummy;
    while (tmp.next != null) {
      if (n <= 0) {
        lastN = lastN.next;
      }
      n--;
      tmp = tmp.next;
    }
    lastN.next = lastN.next.next;

    return dummy.next;
  }

}
