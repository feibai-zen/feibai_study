package com.feibai.spring.study.test.time;

import com.feibai.spring.study.utils.time.TimeFormatUtils;
import org.junit.Test;

import java.time.LocalDateTime;


public final class TimeUtilsTest {
  @Test
  public void test_generate_key() {
    String key = TimeFormatUtils.combineSuffix("original_key", (byte) 5, LocalDateTime.now());
    System.out.println(key);
  }

}
