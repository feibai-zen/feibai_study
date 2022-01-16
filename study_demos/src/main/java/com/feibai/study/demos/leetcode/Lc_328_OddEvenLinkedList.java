package com.feibai.study.demos.leetcode;

import com.feibai.study.demos.leetcode.beans.ListNode;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * 通过次数33,437提交次数53,821
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc_328_OddEvenLinkedList {
  public static void main(String[] args) {
//    Lc_328_OddEvenLinkedList instance = new Lc_328_OddEvenLinkedList();
//    ListNode result = instance.oddEvenList(instance.createList(5));
//    instance.printList(result);

    System.out.println(Integer.valueOf(123).equals(1234));
  }

  public ListNode oddEvenList(ListNode head) {
    int index = 1;
    ListNode odd_head = new ListNode(0);
    ListNode even_head = new ListNode(0);
    ListNode tmp_odd = odd_head;
    ListNode tmp_even = even_head;
    ListNode tmp = head;
    while (tmp != null) {
      if (index % 2 != 0) {
        tmp_odd.next = tmp;
        tmp_odd = tmp;
      } else {
        tmp_even.next = tmp;
        tmp_even = tmp;
      }
      tmp = tmp.next;
      index++;
    }
    tmp_even.next = null;
    tmp_odd.next = even_head.next;
    return odd_head.next;
  }

  private ListNode oddEvenListOffcialSolution(ListNode head) {
    if (head == null) return null;
    ListNode odd = head, even = head.next, evenHead = even;
    while (even != null && even.next != null) {
      odd.next = even.next;
      odd = odd.next;
      even.next = odd.next;
      even = even.next;
    }
    odd.next = evenHead;
    return head;
  }

  private ListNode createList(int cnt) {
    ListNode dummy = new ListNode(0);

    ListNode tmp = dummy;
    for (int i = 1; i <= cnt; i++) {
      tmp.next = new ListNode(i);
      tmp = tmp.next;
    }

    return dummy.next;
  }

  public void printList(ListNode listNode) {
    ListNode tmp = listNode;
    while (tmp != null) {
      System.out.println(tmp.val);
      tmp = tmp.next;
    }
  }

}
