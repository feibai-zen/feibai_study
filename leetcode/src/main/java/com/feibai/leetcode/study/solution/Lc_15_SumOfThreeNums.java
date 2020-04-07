package com.feibai.leetcode.study.solution;

import com.feibai.leetcode.study.beans.ListNode;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class Lc_15_SumOfThreeNums {

  public static void main(String[] args) {
    Lc_15_SumOfThreeNums instance = new Lc_15_SumOfThreeNums();
    int[] nums = {-1, 0, 1, 2, -1, -4};
    System.out.println(instance.directlySolution(nums).toString());
    System.out.println(instance.byHashMap(nums));


  }

  /**
   * 暴力求解：O(n^3)，超出时间范围
   */
  private List<List<Integer>> directlySolution(int[] nums) {
    if (nums == null || nums.length <= 2) {
      return Collections.emptyList();
    }
    Arrays.sort(nums);
    Set<List<Integer>> result = new LinkedHashSet<>();
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          if (nums[i] + nums[j] + nums[k] == 0) {
            List<Integer> value = Arrays.asList(nums[i], nums[j], nums[k]);
            result.add(value);
          }
        }
      }
    }
    return new ArrayList<>(result);
  }

  private List<List<Integer>> byHashMap(int[] nums) {
    if (nums == null || nums.length <= 2) {
      return new ArrayList<>(0);
    }

    Arrays.sort(nums);
    Set<List<Integer>> set = new LinkedHashSet<>();
    Map<Integer, List<Integer>> hashMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int target = 0 - nums[j];
        if (hashMap.containsKey(target)) {
          List<Integer> tmp = hashMap.get(target);
          tmp.add(nums[j]);
          set.add(tmp);
          break;
        } else
          hashMap.put(nums[i] + nums[j], new ArrayList<>(Arrays.asList(nums[i], nums[j])));
      }
    }

    return new ArrayList<>(set);
  }


}
