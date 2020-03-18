package com.feibai.leetcode.study.solution;

import com.feibai.leetcode.study.beans.ListNode;

public class MergeLinkedList {

	public static void main(String[] args) {
		ListNode l1 = null;
		ListNode l2 = new ListNode(1);
		Solution.mergeTwoLists(l1, l2);

	}

}



class Solution {
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode result = null;
		ListNode tmp = null;
		ListNode l1_tmp = l1;
		ListNode l2_tmp = l2;
		int val, a, b;

		while (l1_tmp != null || l2_tmp != null) {
			if (l1_tmp != null) {
				a = l1_tmp.val;
			} else {
				a = Integer.MAX_VALUE;
			}

			if (l2_tmp != null) {
				b = l2_tmp.val;
			} else {
				b = Integer.MAX_VALUE;
			}

			if (a <= b) {
				val = a;
				l1_tmp = l1_tmp == null ? null : l1_tmp.next;
			} else {
				val = b;
				l2_tmp = l2_tmp == null ? null : l2_tmp.next;
			}
			ListNode oneNode = new ListNode(val);
			oneNode.next = null;
			if (null == result) {
				result = oneNode;
				tmp = result;
			}
			tmp.next = oneNode;
			tmp = oneNode;

		}

		return result;
	}
}