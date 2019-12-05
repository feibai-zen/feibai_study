package com.feibai.study.demos.demos.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraverBinaryTree_94 {

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				list.add(cur.val);
				cur = cur.right;
			}
		}
		return list;
	}

	public List<Integer> inorderTraversal_recursive(TreeNode root) {
		List<Integer> rst = new ArrayList<Integer>();
		recursiveInOrderTravelTree(root, rst);
		return rst;
	}

	private void recursiveInOrderTravelTree(TreeNode root, List list) {
		if (null != root) {
			if (root.left != null) {
				recursiveInOrderTravelTree(root.left, list);
			}
			list.add(root.val);
			if (root.right != null) {
				recursiveInOrderTravelTree(root.right, list);
			}
		}
	}

}
