package com.feibai.leetcode.study.solution;

import java.util.*;

public class Lc_15_SumOfThreeNums {

  public static void main(String[] args) {
    Lc_15_SumOfThreeNums instance = new Lc_15_SumOfThreeNums();
    int[] nums = {-1, 0, 1, 2, -1, -4};
    System.out.println(instance.directlySolution(nums).toString());
  }

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




}
