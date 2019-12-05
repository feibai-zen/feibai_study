package com.feibai.study.demos.demos.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	public static void main(String[] args) {
		int[] arr = { -1, 0, 1, 2, -1, -4 };
		threeSum(arr);

	}

	public static List<List<Integer>> threeSum(int[] nums) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums.length >= 3) {
			Arrays.sort(nums);
			for (int i = 0; i < nums.length - 2; i++) {
				if (nums[i] > 0) {
					return result;
				}

				int m = i + 1;
				int n = nums.length - 1;

//				while (m < n) {
//					if ((nums[i] + nums[m] + nums[n]) == 0) {
//						List<Integer> tmp = new ArrayList<>();
//						tmp.add(nums[i]);
//						tmp.add(nums[m]);
//						tmp.add(nums[n]);
//						while() {
//							
//							
//							
//							
//						}
//						
//					
//					} else if ((nums[i] + nums[m] + nums[n]) > 0) {
//						n--;
//						break;
//					} else {
//
//						n++;
//					}
//
//				}
			}
		}
		return result;
	}

	class Solution {
		public List<List<Integer>> threeSum(int[] nums) {
			Arrays.sort(nums);
			List<List<Integer>> ls = new ArrayList<>();

			for (int i = 0; i < nums.length - 2; i++) {
				if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) { // 跳过可能重复的答案

					int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
					while (l < r) {
						if (nums[l] + nums[r] == sum) {
							ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
							while (l < r && nums[l] == nums[l + 1])
								l++;
							while (l < r && nums[r] == nums[r - 1])
								r--;
							l++;
							r--;
						} else if (nums[l] + nums[r] < sum) {
							while (l < r && nums[l] == nums[l + 1])
								l++; // 跳过重复值
							l++;
						} else {
							while (l < r && nums[r] == nums[r - 1])
								r--;
							r--;
						}
					}
				}
			}
			return ls;
		}
	}

}
