package com.feibai.leetcode.study.beans;

/**
 * @Author feibai
 * @Date 2019/12/24 10:07 上午
 **/

public class ListNode {
  public int val;
  public ListNode next;

  ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}
