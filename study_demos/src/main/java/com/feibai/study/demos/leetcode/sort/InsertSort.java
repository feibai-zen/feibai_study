package com.feibai.study.demos.leetcode.sort;

import java.util.Arrays;

public class InsertSort {
  public static void main(String[] args) {

    int[] arr = {6, 5, 3, 1, 8, 7, 2, 4};
    insertSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  private static int[] insertSort(int[] arr) {
    int len = arr.length;
    for (int i = 1; i < len; i++) {
      int c = arr[i];
      int j = i - 1;
      for (; j >= 0; j--) {
        if (c >= arr[j]) {
          break;
        }
        arr[j + 1] = arr[j];
      }

      arr[j + 1] = c;

    }

    return arr;
  }
}