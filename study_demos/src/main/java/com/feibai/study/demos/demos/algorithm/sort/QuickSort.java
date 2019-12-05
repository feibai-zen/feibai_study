package com.feibai.study.demos.demos.algorithm.sort;

import java.util.Arrays;

public class QuickSort {

	public static void quickSort(int[] array) {
		if (array != null) {
			quickSort(array, 0, array.length - 1);
		}
	}

	private static void quickSort(int[] array, int begin, int end) {
		if (begin >= end || array == null)
			return;
		int p = partition_easy(array, begin, end);
		quickSort(array, begin, p - 1);
		quickSort(array, p+1, end);
	}

	private static int partition_easy(int[] array, int begin, int end) {
		int i = begin;
		int j = end;
		// temp就是基准位
		int temp = array[begin];

		while (i < j) {
			// 先看右边，依次往左递减
			while (temp <= array[j] && i < j) {
				j--;
			}
			// 再看左边，依次往右递增
			while (temp >= array[i] && i < j) {
				i++;
			}
			
			// 如果满足条件则交换
			if (i < j) {
				int t = array[j];
				array[j] = array[i];
				array[i] = t;
			}

		}
		// 最后将基准为与i和j相等位置的数字交换
		array[begin] = array[i];
		array[i] = temp;

		return i;
	}

	public static void main(String[] args) {
		int[] arr = { 16, 14, 10, 8, 7, 9, 3, 2, 4, 1 };
		quickSort(arr);
		System.out.println(Arrays.toString(arr));

	}

}
