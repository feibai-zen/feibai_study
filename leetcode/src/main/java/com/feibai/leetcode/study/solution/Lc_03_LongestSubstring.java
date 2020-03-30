package com.feibai.leetcode.study.solution;

import java.util.HashMap;
import java.util.Map;

public class Lc_03_LongestSubstring {

  /**
   * 3. 无重复字符的最长子串
   * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
   * <p>
   * 示例 1:
   * <p>
   * 输入: "abcabcbb"
   * 输出: 3
   * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
   * 示例 2:
   * <p>
   * 输入: "bbbbb"
   * 输出: 1
   * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
   * 示例 3:
   * <p>
   * 输入: "pwwkew"
   * 输出: 3
   * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
   * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
   */

  public static void main(String[] args) {

    try {
      checkPayeeId(Long.valueOf(1), Long.valueOf(1));
    } catch (Exception e) {
      System.out.println("exce");

    }
  }

  public static int lengthOfLongestSubstring(String s) {

    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {

      int start = 0;
      int end = 0;
      Character c = s.charAt(i);

      System.out.println(c);
    }


    return 0;
  }

  public static void checkPayeeId(Long uid, Long payeedId) {
    if (uid.equals(payeedId)) {
      throw new RuntimeException();
    }
  }

}