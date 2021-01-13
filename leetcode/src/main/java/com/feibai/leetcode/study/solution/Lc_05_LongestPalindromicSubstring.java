package com.feibai.leetcode.study.solution;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 没有限制时间复杂度吗？
 */
public class Lc_05_LongestPalindromicSubstring {

  public static void main(String[] args) {
    Lc_05_LongestPalindromicSubstring instance = new Lc_05_LongestPalindromicSubstring();
    System.out.println(instance.longestPalindrome(
        "gphyvqruxjmwhonjjrgumxjhfyupajxbjgthzdvrdqmdouuukeaxhjumkmmhdglqrrohydrmbvtuwstgkobyzjjtdtjroqpyusfsbjlusekghtfbdctvgmqzeybnwzlhdnhwzptgkzmujfldoiejmvxnorvbiubfflygrkedyirienybosqzrkbpcfidvkkafftgzwrcitqizelhfsruwmtrgaocjcyxdkovtdennrkmxwpdsxpxuarhgusizmwakrmhdwcgvfljhzcskclgrvvbrkesojyhofwqiwhiupujmkcvlywjtmbncurxxmpdskupyvvweuhbsnanzfioirecfxvmgcpwrpmbhmkdtckhvbxnsbcifhqwjjczfokovpqyjmbywtpaqcfjowxnmtirdsfeujyogbzjnjcmqyzciwjqxxgrxblvqbutqittroqadqlsdzihngpfpjovbkpeveidjpfjktavvwurqrgqdomiibfgqxwybcyovysydxyyymmiuwovnevzsjisdwgkcbsookbarezbhnwyqthcvzyodbcwjptvigcphawzxouixhbpezzirbhvomqhxkfdbokblqmrhhioyqubpyqhjrnwhjxsrodtblqxkhezubprqftrqcyrzwywqrgockioqdmzuqjkpmsyohtlcnesbgzqhkalwixfcgyeqdzhnnlzawrdgskurcxfbekbspupbduxqxjeczpmdvssikbivjhinaopbabrmvscthvoqqbkgekcgyrelxkwoawpbrcbszelnxlyikbulgmlwyffurimlfxurjsbzgddxbgqpcdsuutfiivjbyqzhprdqhahpgenjkbiukurvdwapuewrbehczrtswubthodv"));
  }

  /**
   * 时间复杂度O(n^3)
   * <p>
   * 思路：将字符串转换成字符数组
   */
  public String longestPalindrome_optimize(String s) {
    if (s.length() > 0) {
      int max_len = 0;

      Map<Integer, String> tmp_map = new HashMap<>();
      for (int i = 0; i < s.length(); i++) {
        for (int j = i + 1; j <= s.length(); j++) {//这里的j的最大值可以等于字符串的长度。因为subString()接口的第二个参数是不包含在子串中的
          String subStr = s.substring(i, j);
          String reverseStr = new StringBuffer(subStr).reverse().toString();//时间复杂度O(n)
          if (subStr.equals(reverseStr) && subStr.length() > max_len) {
            tmp_map.put(subStr.length(), subStr);
            max_len = subStr.length();
          }
        }
      }
      return tmp_map.get(max_len);
    }
    throw new IllegalArgumentException("please input correct string.");
  }

  /**
   * 暴力求解, 时间复杂度O(n^3), 运行超时
   */
  public String longestPalindrome(String s) {
    if (s.length() > 0) {
      int max_len = 0;

      Map<Integer, String> tmp_map = new HashMap<>();
      for (int i = 0; i < s.length(); i++) {
        for (int j = i + 1; j <= s.length(); j++) {//这里的j的最大值可以等于字符串的长度。因为subString()接口的第二个参数是不包含在子串中的
          String subStr = s.substring(i, j);
          String reverseStr = new StringBuffer(subStr).reverse().toString();//时间复杂度O(n)
          if (subStr.equals(reverseStr) && subStr.length() > max_len) {
            tmp_map.put(subStr.length(), subStr);
            max_len = subStr.length();
          }
        }
      }
      return tmp_map.get(max_len);
    }
    throw new IllegalArgumentException("please input correct string.");
  }
}
