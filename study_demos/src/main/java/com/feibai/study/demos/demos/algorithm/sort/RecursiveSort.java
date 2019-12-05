package com.feibai.study.demos.demos.algorithm.sort;

import java.util.Arrays;

/**
 * （1）稳定性
     　归并排序是一种稳定的排序。
（2）存储结构要求
    　可用顺序存储结构。也易于在链表上实现。
（3）时间复杂度
    　对长度为n的文件，需进行趟二路归并，每趟归并的时间为O(n)，故其时间复杂度无论是在最好情况下还是在最坏情况下均是O(nlgn)。
（4）空间复杂度
   　 需要一个辅助向量来暂存两有序子文件归并的结果，故其辅助空间复杂度为O(n)，显然它不是就地排序。
  注意：
    　若用单链表做存储结构，很容易给出就地的归并排序
 * @author leeyuanlong
 *
 * @Time 2019年8月21日
 *
 */
public class RecursiveSort {

	public static void main(String[] args) {

		int[] arr = { 6, 5, 3, 1, 8, 7, 2, 4 };
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	private static int[] mergeSort(int[] arr) {
		sort(arr, 0, arr.length - 1);
		return arr;

	}

	public static int[] sort(int[] a, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			sort(a, low, mid);
			sort(a, mid + 1, high);
			// 左右归并
			merge(a, low, mid, high);
		}
		return a;
}

	public static void merge(int[] a, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		// 把较小的数先移到新数组中
		while (i <= mid && j <= high) {
			if (a[i] < a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}
		// 把左边剩余的数移入数组
		while (i <= mid) {
			temp[k++] = a[i++];
		}
		// 把右边边剩余的数移入数组
		while (j <= high) {
			temp[k++] = a[j++];
		}
		// 把新数组中的数覆盖nums数组
		for (int x = 0; x < temp.length; x++) {
			a[x + low] = temp[x];
		}
	}
}