package com.feibai.study.demos.leetcode;

public class Lc_28_strStr {
  public static void main(String[] args) {
    strStr("q","q");

  }

  public static int strStr(String haystack, String needle) {
    if (haystack == null || needle == null || needle.isEmpty()) {
      return 0;
    }

    for (int i = 0; i < haystack.length(); i++) {
      if (haystack.charAt(i) == needle.charAt(0)) {
        for (int j = 0; j < needle.length(); j++) {
          if (needle.charAt(j) != haystack.charAt(i + j)) {
            break;
          }
          if (j == needle.length() - 1) {
            return i;
          }
        }
      }
    }

    return -1;
  }
}
