package com.feibai.study.demos.leetcode;

/**
 * @Author feibai
 * @Date 2019/12/24 10:14 上午
 **/

import com.feibai.study.demos.leetcode.beans.ListNode;

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
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        instance.constructList(l1, l2);
        instance.printList(instance.addTwoNumbers(l1.next, l2.next));
    }

    /**
     * 解法:
     * 总结：1）返回虚拟头节点的next  2）对链表进行操作时，应该定义分身，对分身进行操作
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            throw new IllegalArgumentException("链表不能为null.");
        }

        ListNode dummyHead = new ListNode(0);
        ListNode tmp = dummyHead;
        int flag = 0;
        while (l1 != null || l2 != null || flag != 0) {//如果l1、l2都遍历完了,但是flag不是0,计算仍然没有结束
            int var1 = 0;
            int var2 = 0;
            if (l1 != null) {
                var1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                var2 = l2.val;
                l2 = l2.next;
            }
            flag = (var1 + var2 + flag) / 10;
            ListNode newNode = new ListNode((var1 + var2 + flag) % 10);
            tmp.next = newNode;
            tmp = newNode;
        }

        return dummyHead.next;
    }

    private void constructList(ListNode l1, ListNode l2) {
        // (2 -> 4 -> 3) + (5 -> 6 -> 4)
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        l2.next = new ListNode(5);
        l2.next.next = new ListNode(6);
        l2.next.next.next = new ListNode(4);
    }

    private void printList(ListNode l) {
        while (l != null) {
            System.out.println(l.val);
            l = l.next;
        }
    }
}
