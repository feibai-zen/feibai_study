package com.feibai.leetcode.study.solution;

/**
 * @Author feibai
 * @Date 2019/12/24 10:14 上午
 **/

import com.feibai.leetcode.study.beans.ListNode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */


public class Lc_02_AddTwoNums {
  public static void main(String[] args) {
    Lc_02_AddTwoNums instance = new Lc_02_AddTwoNums();

    instance.addTwoNumbers(new ListNode(10), new ListNode(10));
  }

  /**
   * 解法:
   * 总结：1）返回虚拟头节点的next  2）对链表进行操作时，应该定义分身，对分身进行操作
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      throw new IllegalArgumentException("输入的参数链表为null!");
    }
    int flag = 0;
    ListNode ret = new ListNode(0);//虚拟头节点dummyHead
    ListNode tmp_ret = ret;
    while (true) {
      int l1_val = 0;
      int l2_val = 0;
      int sum = 0;
      if (l1 == null && l2 == null && flag == 0) {
        break;
      }
      if (l1 != null) {
        l1_val = l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        l2_val = l2.val;
        l2 = l2.next;
      }
      sum = (l1_val + l2_val + flag) % 10;
      flag = (l1_val + l2_val + flag) / 10;

      ListNode new_node = new ListNode(sum);
      tmp_ret.next = new_node;
      tmp_ret = new_node;
    }

    return ret.next;
  }

}
