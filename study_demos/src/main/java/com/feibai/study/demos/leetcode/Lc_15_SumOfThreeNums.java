package com.feibai.study.demos.leetcode;

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
//    int[] nums = {-1, 0, 1, 2, -1, -4};
    int[] nums = {1, 2, -2, -1};
    System.out.println(instance.directlySolution(nums).toString());
    System.out.println(instance.byHashMap(nums));
    System.out.println(instance.byDoublePointer(nums));

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

  /**
   * 使用hashMap辅助求解：O(n^2)
   */
  private List<List<Integer>> byHashMap(int[] nums) {
    if (nums == null || nums.length <= 2) {
      return new ArrayList<>(0);
    }

    Arrays.sort(nums);//对数组进行排序，使用快排O(NlogN)
    Set<List<Integer>> set = new LinkedHashSet<>();
    Map<Integer, List<Integer>> hashMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int target = 0 - nums[i] - nums[j];
        if (hashMap.containsKey(nums[j])) {
          List<Integer> tmp = hashMap.get(nums[j]);
          if (tmp.size() < 3) {
            tmp.add(nums[j]);
          }
          set.add(tmp);
        } else
          hashMap.put(target, new ArrayList<>(Arrays.asList(nums[i], nums[j])));
      }
    }

    return new ArrayList<>(set);
  }

  /**
   * 官方题解思路
   * 标签：数组遍历
   * 首先对数组进行排序，排序后固定一个数 nums[i]，再使用左右指针指向 nums[i]后面的两端，数字分别为 nums[L] 和 nums[R]，计算三个数的和 sum判断是否满足为 0，满足则添加进结果集
   * 如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环
   * 如果 nums[i] == nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
   * 当 sum=0 时，nums[L] = nums[L+1] 则会导致结果重复，应该跳过，L++
   * 当 sum = 0 时，nums[R]= nums[R-1] 则会导致结果重复，应该跳过，R--
   * 时间复杂度：O(n^2)
   */
  private List<List<Integer>> byDoublePointer(int[] nums) {
    List<List<Integer>> ans = new ArrayList();
    int len = nums.length;
    if (nums == null || len < 3) return ans;

    Arrays.sort(nums); // 排序
    for (int i = 0; i < len; i++) {
      if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
      if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
      int L = i + 1;
      int R = len - 1;
      while (L < R) {
        int sum = nums[i] + nums[L] + nums[R];
        if (sum == 0) {
          ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
          while (L < R && nums[L] == nums[L + 1]) L++; // 去重
          while (L < R && nums[R] == nums[R - 1]) R--; // 去重
          L++;
          R--;
        } else if (sum < 0) L++;
        else if (sum > 0) R--;
      }
    }
    return ans;
  }

}
