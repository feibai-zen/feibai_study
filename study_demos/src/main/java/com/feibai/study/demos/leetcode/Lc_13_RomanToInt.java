package com.feibai.study.demos.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Lc_13_RomanToInt {
    Map<Character, Integer> map = new HashMap<>();
    Map<String, Integer> mapStr = new HashMap<>();

    {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    {
        mapStr.put("IV", 4);
        mapStr.put("IX", 9);
        mapStr.put("XL", 40);
        mapStr.put("XC", 90);
        mapStr.put("CM", 900);
        mapStr.put("CD", 400);
    }

    public int romanToInt(String s) {
        int sum = 0;
        Set<Map.Entry<String, Integer>> entries = mapStr.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            if (s.contains(key)) {
                s = s.replace(key, "");
                sum += value;
            }
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(s.charAt(i))) {
                sum += map.get(s.charAt(i));
            }
        }

        return sum;
    }

    //read csv file and transfer to list with Java
    public static void main(String[] args) {

    }

}

