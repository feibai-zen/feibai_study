package com.feibai.leetcode.study.solution;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 */

public class Lc_04_MedianofTwoSortedArrays {

  public static void main(String[] args) {

  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int[] arr = new int[m + n];
    int k = 0;
    if ((m + n) % 2 == 0) {
      k = (m + n) / 2;
    } else {
      k = (m + n) / 2 + 1;
    }

    int i = 0, j = 0;
    while (i < m && j < n) {
      int tmp_num1 = nums1[i];
      int tmp_num2 = nums2[j];
      double ans = 0;
      if (tmp_num1 <= tmp_num2) {
        ans = (double) nums1[i];
        i++;
      } else {
        ans = nums2[j];
        j++;
      }
      if (i + j == k) {
        return ans;
      }

      return (ans1 + ans2) / 2;
    }

    return 0;
  }

}
