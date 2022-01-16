package com.feibai.study.demos.leetcode;

/**
 * 题目：给定两个大小为 m 和 n 的有序数组nums1 和nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为O(log(m + n))。
 * <p>
 * 你可以假设nums1和nums2不会同时为空。
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
 * <p>
 * 解题思路：插入排序将两个数组合并到一个。插排的时间复杂度为O(log(n))
 */

public class Lc_04_MedianofTwoSortedArrays {

  public static void main(String[] args) {
    Lc_04_MedianofTwoSortedArrays instance = new Lc_04_MedianofTwoSortedArrays();
    int[] nums1 = new int[]{1, 3};
    int[] nums2 = new int[]{2};
    System.out.println(instance.findMedianSortedArrays(nums1, nums2));
  }

  /**
   * 时间复杂度O(m+n)。是否存在O(log(m+n))
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int[] arr = new int[(m + n) / 2 + 1];

    int i = 0, j = 0, q = 0;
    while (i < m || j < n) {
      int tmp_num1 = Integer.MAX_VALUE;
      int tmp_num2 = Integer.MAX_VALUE;

      if (i < m) {
        tmp_num1 = nums1[i];
      }
      if (j < n) {
        tmp_num2 = nums2[j];
      }

      if (tmp_num1 <= tmp_num2) {
        arr[q++] = tmp_num1;
        i++;
      } else {
        arr[q++] = tmp_num2;
        j++;
      }

      if (i + j >= (m + n) / 2 + 1) {
        break;
      }
    }

    //处理返回值
    if ((m + n) % 2 == 0) {
      return ((double) arr[(m + n) / 2 - 1] + (double) arr[(m + n) / 2]) / 2;
    } else {
      return arr[(m + n) / 2];
    }
  }

}
