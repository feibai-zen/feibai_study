package com.feibai.study.demos.leetcode;


import com.feibai.study.demos.leetcode.beans.TreeNode;

public class Lc_100_SameTree {

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