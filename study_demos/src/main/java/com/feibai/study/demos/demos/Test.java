package com.feibai.study.demos.demos;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.IntStream;

public class Test {


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

  /**
   * 将uids按照活动规则进行分组存入redis
   */
  public static void groupUidsToRedis(List uids) {
    if (uids.isEmpty()) {
      return;
    }
    Map<String, String> idToGroup = new HashMap(uids.size());
    int step = 10;
    int index = 0;
    int i = 0;
    String racer = "racer_catching_";
    String groupName;
    while (index < uids.size() - step && i <= 10) {
      if (i == 0) {
        step = 10;
        groupName = "racer_king";
      } else {
        step = 11;
        groupName = racer + i;
      }
      for (Object uid : uids.subList(index, index + step)) {
        idToGroup.put(uid.toString(), groupName);
      }
      index += step;
      i++;
    }
//    RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
//    stringRedisTemplate.executePipelined((RedisCallback<Object>) connection -> {
//      for (String uid : idToGroup.keySet()) {
//        connection.set(serializer.serialize(uid), serializer.serialize(idToGroup.get(uid)));
//      }
//      return null;
//    });
  }

  public static void main(String[] args) {
    List<String> uids = Lists.newArrayList();
    Iterator<Integer> iterator = IntStream.range(1, 120).boxed().iterator();
    while (iterator.hasNext()) {
      uids.add(iterator.next().toString());
    }

    groupUidsToRedis(uids);
  }
}
