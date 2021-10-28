package com.feibai.spring.study.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IRedisKeySpace {

  String buildStarRankFansWeekKey(long majorKey, int offset);

  String buildStarRankStarDailyKey(int offset);

  String buildStarRankStarWeekKey(int offset);

  String buildStarRankStarHourKey(int offset);

  String buildStarRankStarHourKey(LocalDateTime dateTime);

  String buildStarRankStarDailyKey(LocalDate date);

  String buildStarRankStarWeekKey(int year, int weekOfYear);

  String buildStarRankStarWeekKey(LocalDate date);

  String buildStarRankStarTotalKey();

  String buildStarLiveTotalRankMonthKey(int offset);

}
