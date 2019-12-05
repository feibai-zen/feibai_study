package com.my.study.demos.algorithm.sort;

import java.util.Arrays;

/**
 * 
 *最差时间复杂度 ---- O(n^2)
 *最优时间复杂度 ---- O(n^2)
 *平均时间复杂度 ---- O(n^2) 
 *所需辅助空间 ------ O(1)
*稳定性 ------------ 不稳定
 * @author leeyuanlong
 *
 * @Time 2019年8月21日
 *
 */
public class SimpleSelectSort {

	public static void main(String[] args) {
		int[] arr = { 6, 5, 3, 1, 8, 7, 2, 4 };
		selectSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static int[] selectSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			int min = i;
			int j = i + 1;
			for (; j < len; j++) {
				if(arr[min] > arr[j]) {
					min = j;
				}
			}
			int tmp = arr[i];
			arr[i] = arr[min];
			arr[min] = tmp;
			
		}

		return arr;
	}
}
