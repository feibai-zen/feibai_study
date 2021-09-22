package com.feibai.spring.study.test.normal;

import java.util.Arrays;

import org.junit.Test;

public final class StringTest {

  @Test
  public void test_string_split() {
    String multiKey = "haha.heihei.haha";

    String[] keys = multiKey.split("\\.");
    System.out.println(Arrays.asList(keys));
  }
}
