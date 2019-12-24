package com.feibai.study.demos.demos.leetcode.solution;

import com.feibai.study.demos.demos.leetcode.beans.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelOrderTraversBinaryTree {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> rst = new ArrayList<List<Integer>>();
    if (root != null) {
      LinkedList<TreeNode> nextlevel = new LinkedList<TreeNode>();
      nextlevel.add(root);
      while (nextlevel.size() != 0) {
        List<Integer> tmp = new ArrayList<Integer>();
        LinkedList<TreeNode> tmp_next_level = new LinkedList<TreeNode>();
        for (TreeNode node : nextlevel) {
          if (node != null) {
            tmp.add(node.val);
            tmp_next_level.add(node.left);
            tmp_next_level.add(node.right);
          }
        }
        nextlevel.clear();
        nextlevel = tmp_next_level;

        if (tmp.size() != 0) {
          rst.add(tmp);
        }
      }
    }
    return rst;
  }

}
