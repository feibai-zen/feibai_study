package com.feibai.leetcode.study.solution;

import com.feibai.leetcode.study.beans.ListNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Lc_1260_ShiftGrid {
  public static void main(String[] args) {
    int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    System.out.println(shiftGrid(arr, 9));
  }

  public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
    List<List<Integer>> ret = new LinkedList<>();
    int m = 0, n = 0;
    if (grid.length > 0) {
      m = grid.length;
    }
    n = grid[0].length;
    if (m != 0 && n != 0) {
      //创建链表
      ListNode dummyNode = new ListNode(-1);
      ListNode current = dummyNode;

      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          current.next = new ListNode(grid[i][j]);
          current = current.next;
        }
      }

      //遍历链表，找到 m * n -k节点以及最后一个节点最后一个
      current = dummyNode;
      ListNode lastK = null, LastOne = null;
      int i = 0;
      while (current.next != null) {
        if (i == m * n - (k % (m * n)) - 1) {
          lastK = current.next;
        }
        if (i == m * n - 1) {
          LastOne = current.next;
        }
        current = current.next;
        i++;
      }
      //重组链表
      LastOne.next = dummyNode.next;
      dummyNode.next = lastK.next;
      lastK.next = null;

      int cursor = -1;
      current = dummyNode;
      List<Integer> innerList = null;
      while (current.next != null) {
        ListNode p = current.next;
        cursor++;
        if (cursor % n == 0) {
          innerList = new LinkedList<>();
          ret.add(innerList);
        }
        innerList.add(p.val);
        current = current.next;
      }
    }
    return ret;
  }

}