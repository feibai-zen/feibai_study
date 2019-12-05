package com.feibai.study.demos.demos.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TwoSum_01
 * @Description LeetCode:1.两数之和
 * @Author leeyuanlong
 * @Date 2019/11/18 6:25 下午
 **/

public class TwoSum_01 {

  public static void main(String[] args) {
    int[] arr = new int[]{2, 7, 11, 15, 20, 1};

    System.out.println(Arrays.asList(twoSum(arr, 9)).toString());
  }

  public static int[] twoSum(int[] nums, int target) {
    int[] ret = new int[2];
    Map<Integer, Integer> numMap = new HashMap<>();
    for(int i = 0; i < nums.length; i++){
      numMap.put(target - nums[i],i);
      int tmp = target - nums[i];
      if(numMap.containsKey(tmp)) {
        ret[0] = i;
        ret[1] = numMap.get(tmp);
        break;
      }
    }

    return ret;
  }
}
