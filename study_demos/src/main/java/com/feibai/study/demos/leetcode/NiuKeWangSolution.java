package com.feibai.study.demos.leetcode;

import java.util.*;

public class NiuKeWangSolution {
  public static void main(String[] args) {
    String[] arr = {
        "apple",
        "iOS",
        "dog",
        "nana",
        "man",
        "good",
        "goodman"
    };
    NiuKeWangSolution niuKeWangSolution = new NiuKeWangSolution();
    System.out.println(niuKeWangSolution.longestWord(arr));
  }

  public String longestWord(String[] words) {
    if (words.length == 0) {
      return "";
    }
    Map<String, Boolean> resultMapping = new HashMap<>();
    return getLongestWords(words, resultMapping);
  }

  private String getLongestWords(String[] words, Map<String, Boolean> resultMap) {
    if (words.length == 0) {
      return "";
    }
    List<String> wordsList = Arrays.asList(words);
    Collections.sort(wordsList);
    int size = wordsList.size();
    for (int i = 0; i < size; i++) {
      String word = wordsList.get(i);
      if (canBeBuild(word, wordsList, true, resultMap)) {
        return word;
      }
    }

    return "";
  }

  private boolean canBeBuild(String word, List<String> words, Boolean isOriginal, Map<String, Boolean> resultMapping) {
    if (resultMapping.get(word) != null && !isOriginal) {
      return resultMapping.get(word);
    }

    int length = word.length();
    for (int i = 1; i <= length; i++) {
      String leftStr = word.substring(0, i);
      String rightStr = word.substring(i, length);

      canBeBuild(leftStr, words, false, resultMapping);
      canBeBuild(rightStr, words, false, resultMapping);

      if (resultMapping.get(leftStr) != null
          && resultMapping.get(leftStr)
          && words.contains(leftStr)
          && canBeBuild(rightStr, words, false, resultMapping)) {
        return true;
      }
    }
    resultMapping.put(word, false);
    return false;
  }

}
