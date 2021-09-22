package com.feibai.spring.study.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Le_01_TwoSum {

  @Test
  public void testTwoSum() {
    int target = 25;
    int[] arr = new int[]{2, 7, 9, 34, 16};

    System.out.println(Arrays.toString(twoSum(arr, target)));
  }

  private int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];
    Map<Integer, Integer> tmpMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (tmpMap.get(target - nums[i]) != null) {
        result[0] = tmpMap.get(target - nums[i]);
        result[1] = i;
        break;
      } else {
        tmpMap.put(nums[i], i);
      }
    }

    return result;
  }
}
