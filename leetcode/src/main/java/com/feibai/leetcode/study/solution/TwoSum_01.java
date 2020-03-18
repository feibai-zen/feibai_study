package com.feibai.leetcode.study.solution;

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

public class TwoSum_01 {

  public static void main(String[] args) {
    int[] arr = new int[]{2, 7, 11, 15, 20, 1};

    System.out.println(Arrays.asList(twoSum(arr, 9)).toString());
  }

  public static int[] twoSum(int[] nums, int target) {
    int[] ret = new int[2];
    Map<Integer, Integer> numMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      numMap.put(target - nums[i], i);
      int tmp = target - nums[i];
      if (numMap.containsKey(tmp)) {
        ret[0] = i;
        ret[1] = numMap.get(tmp);
        break;
      }
    }
    throw new IllegalArgumentException("No two sum solution");
  }

  public static int[] solution_force(int[] num, int target) {

    int[] ret = new int[2];




    return null;
  }
}
