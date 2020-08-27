package com.feibai.study.demos.demos;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Test {

  public static void main(String[] args) {
//    System.out.println(test_LocalDate());
//    System.out.println(test_LocalDate2());
  }

  public void test_001() {
    List<Integer> tempModelList = JSON.parseArray("[123,456,789]", Integer.class);
    System.out.println(tempModelList);
  }

  public static String test_LocalDate() {
    LocalDate localDate = LocalDate.now().plusDays(0);
    LocalDate formatLocalDate = localDate.minusDays((long) (localDate.getDayOfWeek().getValue() - 1));
    StringBuilder sb = new StringBuilder();
    sb.append("gift_fans_live_total_rank_month_").append(String.format("%4d%02d", formatLocalDate.getYear(), formatLocalDate.getMonth()));

    return sb.toString();
  }

  public static String test_LocalDate2() {
    LocalDate localDate = LocalDate.now().plusWeeks(0);
    TemporalField weekBasedYear = WeekFields.of(DayOfWeek.MONDAY, 7).weekOfWeekBasedYear();
    LocalDate formatLocalDate = localDate.minusDays((long) (localDate.getDayOfWeek().getValue() - 1));
    StringBuilder sb = new StringBuilder();
    sb.append("test_redis_key").append(String.format("%4d%02d", formatLocalDate.getYear(), formatLocalDate.get(weekBasedYear)));

    return sb.toString();
  }

  private boolean test_list_is_instanceof_collection() {
    List<Integer> test = Collections.singletonList(1);
    return test instanceof Collection;
  }
}
