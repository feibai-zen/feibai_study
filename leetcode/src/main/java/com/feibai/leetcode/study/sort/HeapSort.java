package com.feibai.leetcode.study.sort;

import java.util.Arrays;

/**
 * 堆排序：（大根堆）
 * <p>
 * ①将存放在array[0，...，n-1]中的n个元素建成初始堆；
 * <p>
 * ②将堆顶元素与堆底元素进行交换，则序列的最大值即已放到正确的位置；
 * <p>
 * ③但此时堆被破坏，将堆顶元素向下调整使其继续保持大根堆的性质，再重复第②③步，直到堆中仅剩下一个元素为止。
 * <p>
 * 堆排序算法的性能分析：
 * <p>
 * 空间复杂度:o(1)；
 * <p>
 * 时间复杂度:建堆：O(n)，每次调整O(logn)，故最好、最坏、平均情况下：O(n*logn);
 * <p>
 * 稳定性：不稳定
 *
 * @author feibai
 * @Time 2019年8月21日
 */
public class HeapSort {
  public static void main(String[] args) {
    int[] arr = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
    headSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  /**
   * 堆排序
   */
  public static void headSort(int[] array) {
    // 构造初始堆,从第一个非叶子节点开始调整,左右孩子节点中较大的交换到父节点中
    for (int i = (array.length) / 2 - 1; i >= 0; i--) {
      headAdjust(array, i, array.length);
    }

    // 排序，将最大的节点放在数组最后，然后从根节点重新调整
    for (int i = array.length - 1; i >= 1; i--) {
      swap(array, 0, i);
      headAdjust(array, 0, i);
    }
  }

  private static void headAdjust(int[] array, int i, int len) {
    //index是左孩子
    int k = i, temp = array[i], index = 2 * k + 1;
    while (index < len) {
      // 如果存在右孩子
      if (index + 1 < len && array[index] < array[index + 1]) {
        // 如果左孩子小于右孩子
        index = index + 1;
      }

      if (array[index] > temp) {
        // 如果孩子节点大于父节点
        array[k] = array[index];
        // 将孩子节点与父节点交换
        k = index;
        index = 2 * k + 1;
      } else {
        break;
      }
    }
    array[k] = temp;
  }

  private static void swap(int[] array, int a, int b) {
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
  }
}