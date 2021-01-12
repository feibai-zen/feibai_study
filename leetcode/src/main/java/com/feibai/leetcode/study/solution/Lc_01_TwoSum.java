package com.feibai.leetcode.study.solution;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TwoSum_01
 * @Description LeetCode:1.两数之和
 **/

/**
 * 说明：
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */

public class Lc_01_TwoSum {
  public static void main(String[] args) {
    int[] arr = new int[]{2, 7, 11, 15, 20, 1};

    System.out.println(Arrays.toString(twoSum(arr, 9)));

    System.out.println(Arrays.toString(solution_by_violence(arr, 9)));
  }

  /**
   * O(n) 用空间换时间
   */
  public static int[] twoSum(int[] nums, int target) {
    int[] ret = new int[2];
    // Map中的key--num[i]; value--索引
    Map<Integer, Integer> numMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int tmp = target - nums[i];
      if (numMap.containsKey(tmp)) {
        ret[0] = numMap.get(tmp);
        ret[1] = i;
        return ret;
      }
      numMap.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
  }

  /**
   * O(n^2)
   */
  public static int[] solution_by_violence(int[] num, int target) {
    int[] ret = new int[2];
    for (int i = 0; i < num.length; i++) {
      for (int j = i + 1; j < num.length; j++) {
        if (num[i] + num[j] == target) {
          ret[0] = i;
          ret[1] = j;
        }
      }
    }

    return ret;
  }
}
