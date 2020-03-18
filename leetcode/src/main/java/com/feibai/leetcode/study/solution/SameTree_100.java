package com.feibai.leetcode.study.solution;


import com.feibai.leetcode.study.beans.TreeNode;

public class SameTree_100 {

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}

		if (p != null && q != null && p.val == q.val) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		} else {
			return false;
		}
	}
}