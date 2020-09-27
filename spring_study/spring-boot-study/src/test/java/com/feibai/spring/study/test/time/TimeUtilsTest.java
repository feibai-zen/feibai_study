package com.feibai.spring.study.test.time;

import com.feibai.spring.study.utils.time.TimeFormatUtils;
import com.feibai.spring.study.utils.time.TimeSuffixEnum;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;


public final class TimeUtilsTest {
  @Test
  public void test_generate_key() {
    String key = TimeFormatUtils.combineSuffix("original_key", (byte) 5, LocalDateTime.now());
    System.out.println(key);
  }

}
