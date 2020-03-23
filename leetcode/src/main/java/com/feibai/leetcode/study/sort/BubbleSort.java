package com.feibai.leetcode.study.sort;

import java.util.Arrays;

/**
 * 
 * @author feibai
 *
 * @Time 2019年8月21日
 *
 */

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = { 6, 5, 3, 1, 8, 7, 2, 4 };
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static int[] bubbleSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			boolean flag = false;
			for (int j = 1; j < len - i; j++) {
				if (arr[j - 1] > arr[j]) {
					int tmp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = tmp;
					flag = true;
				}
				
			}
			if (!flag) {
				break;
			}
		}
		
		
		return arr;
	}

}
