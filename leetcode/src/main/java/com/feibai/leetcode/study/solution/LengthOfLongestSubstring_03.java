package com.feibai.leetcode.study.solution;

/**
 * @Author feibai
 * @Date 2019/12/25 3:59 下午
 **/

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring_03 {

  public static void main(String[] args) {
    LengthOfLongestSubstring_03 test = new LengthOfLongestSubstring_03();
    System.out.println(test.lengthOfLongestSubstring("haahaha"));
  }

  /**
   * 暴力解法，时间复杂度O(n^3)
   */
  public int lengthOfLongestSubstring(String s) {
    int max = 0;
    for (int i = 0; i < s.length(); i++) {
      int count = 0;
      StringBuilder sb = new StringBuilder();
      for (int j = i; j < s.length(); j++) {
        String tmp = String.valueOf(s.charAt(j));
        if (sb.toString().contains(tmp)) {
          if (count > max) {
            max = count;
          }
          break;
        } else {
          sb.append(s.charAt(j));
          count++;
        }
      }
      if (count > max) {
        max = count;
      }
    }
    return max;
  }


  /**
   * 官方解法1
   */
  public int lengthOfLongestSubstring_solution2(String s) {
    int n = s.length();
    int ans = 0;
    for (int i = 0; i < n; i++)
      for (int j = i + 1; j <= n; j++)
        if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
    return ans;
  }

  public boolean allUnique(String s, int start, int end) {
    Set<Character> set = new HashSet<>();
    for (int i = start; i < end; i++) {
      Character ch = s.charAt(i);
      if (set.contains(ch)) return false;
      set.add(ch);
    }
    return true;
  }


}
