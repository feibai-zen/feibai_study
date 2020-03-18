package com.feibai.leetcode.study.solution;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring_03 {

  public static void main(String[] args) {

      try{
        checkPayeeId(Long.valueOf(1),Long.valueOf(1));
      }catch(Exception e){
        System.out.println("exce");

      }
  }

  public static int lengthOfLongestSubstring(String s) {

    Map<Character,Integer> map = new HashMap<>();
    for(int i = 0; i<s.length(); i++){

      int start = 0;
      int end = 0;
      Character c = s.charAt(i);

      System.out.println(c);
    }


    return 0;
  }

  public static void checkPayeeId(Long uid, Long payeedId){
    if (uid.equals(payeedId)) {
      throw new RuntimeException();
    }
  }

}