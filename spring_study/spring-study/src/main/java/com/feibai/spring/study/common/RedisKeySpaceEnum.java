package com.feibai.spring.study.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;


/**
 * 对Redis key统一命名管理
 */
public enum RedisKeySpaceEnum implements IRedisKeySpace {
  OLD {
    @Override
    public String buildStarRankFansWeekKey(long majorKey, int offset) {
      TemporalField weekBasedYear = WeekFields.of(DayOfWeek.MONDAY, 7).weekOfWeekBasedYear();
      LocalDate date = LocalDate.now().plusWeeks(offset);
      date = date.minusDays((long) (date.getDayOfWeek().getValue() - 1));
      StringBuilder sb = new StringBuilder();
      return sb.append("springboot_study_week_").append(String.format("%4d%02d", date.getYear(), date.get(weekBasedYear)))
          .append("_").append(majorKey).toString();
    }

    @Override
    public String buildStarRankStarHourKey(int offset) {
      LocalDateTime dateTime = LocalDateTime.now().plusHours(offset);
      StringBuilder sb = new StringBuilder();
      sb.append("springboot_study_hour_").append(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)).append("_").append(
          dateTime.getHour());
      return sb.toString();
    }

    @Override
    public String buildStarRankStarHourKey(LocalDateTime dateTime) {
      StringBuilder sb = new StringBuilder();
      sb.append("springboot_study_hour_").append(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)).append("_").append(
          dateTime.getHour());
      return sb.toString();
    }

    @Override
    public String buildStarRankStarDailyKey(int offset) {
      LocalDate date = LocalDate.now().plusDays(offset);
      return "springboot_study_daily_" + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String buildStarRankStarWeekKey(int offset) {
      TemporalField weekBasedYear = WeekFields.of(DayOfWeek.MONDAY, 7).weekOfWeekBasedYear();
      LocalDate date = LocalDate.now().plusWeeks(offset);
      date = date.minusDays((long) (date.getDayOfWeek().getValue() - 1));
      return buildStarRankStarWeekKey(date.getYear(), date.get(weekBasedYear));
    }

    @Override
    public String buildStarRankStarDailyKey(LocalDate date) {
      return "springboot_study_daily_" + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String buildStarRankStarWeekKey(int year, int weekOfYear) {
      StringBuilder sb = new StringBuilder();
      return sb.append("springboot_study_week_").append(String.format("%4d%02d", year, weekOfYear)).append("_")
          .toString();
    }

    @Override
    public String buildStarRankStarWeekKey(LocalDate localDate) {
      TemporalField weekBasedYear = WeekFields.of(DayOfWeek.MONDAY, 7).weekOfWeekBasedYear();
      LocalDate formatLocalDate = localDate.minusDays((long) (localDate.getDayOfWeek().getValue() - 1));
      return buildStarRankStarWeekKey(formatLocalDate.getYear(), formatLocalDate.get(weekBasedYear));
    }

    @Override
    public String buildStarRankStarTotalKey() {
      StringBuilder sb = new StringBuilder();
      return sb.append("springboot_study_total").toString();
    }

    @Override
    public String buildStarLiveTotalRankMonthKey(int offset) {
      LocalDate localDate = LocalDate.now().plusMonths(offset);
      StringBuilder sb = new StringBuilder();
      sb.append("Star_live_total_rank_month_").append(localDate.getYear()).append(localDate.getMonth().toString());

      return sb.toString();
    }

  }, V1 {
    @Override
    public String buildStarRankFansWeekKey(long majorKey, int offset) {
      return appendPostFix(OLD.buildStarRankFansWeekKey(majorKey, offset));
    }

    @Override
    public String buildStarRankStarHourKey(int offset) {
      return appendPostFix(OLD.buildStarRankStarHourKey(offset));
    }

    @Override
    public String buildStarRankStarHourKey(LocalDateTime dateTime) {
      return appendPostFix(OLD.buildStarRankStarHourKey(dateTime));
    }

    @Override
    public String buildStarRankStarDailyKey(int offset) {
      return appendPostFix(OLD.buildStarRankStarDailyKey(offset));
    }

    @Override
    public String buildStarRankStarWeekKey(int offset) {
      return appendPostFix(OLD.buildStarRankStarWeekKey(offset));
    }

    @Override
    public String buildStarRankStarTotalKey() {
      return appendPostFix(OLD.buildStarRankStarTotalKey());
    }

    @Override
    public String buildStarRankStarDailyKey(LocalDate date) {
      return appendPostFix(OLD.buildStarRankStarDailyKey(date));
    }

    @Override
    public String buildStarRankStarWeekKey(int year, int weekOfYear) {
      return appendPostFix(OLD.buildStarRankStarWeekKey(year, weekOfYear));
    }

    @Override
    public String buildStarRankStarWeekKey(LocalDate localDate) {
      return appendPostFix(OLD.buildStarRankStarWeekKey(localDate));
    }

    @Override
    public String buildStarLiveTotalRankMonthKey(int offset) {
      return appendPostFix(OLD.buildStarLiveTotalRankMonthKey(offset));
    }

    private String appendPostFix(String target) {
      return target + "_new";
    }
  }, V2 {
    @Override
    public String buildStarRankFansWeekKey(long majorKey, int offset) {
      return appendPostFix(OLD.buildStarRankFansWeekKey(majorKey, offset));
    }

    @Override
    public String buildStarRankStarHourKey(int offset) {
      return appendPostFix(OLD.buildStarRankStarHourKey(offset));
    }

    @Override
    public String buildStarRankStarHourKey(LocalDateTime dateTime) {
      return appendPostFix(OLD.buildStarRankStarHourKey(dateTime));
    }

    @Override
    public String buildStarRankStarDailyKey(int offset) {
      return appendPostFix(OLD.buildStarRankStarDailyKey(offset));
    }

    @Override
    public String buildStarRankStarWeekKey(int offset) {
      return appendPostFix(OLD.buildStarRankStarWeekKey(offset));
    }

    @Override
    public String buildStarRankStarTotalKey() {
      return appendPostFix(OLD.buildStarRankStarTotalKey());
    }

    @Override
    public String buildStarRankStarDailyKey(LocalDate date) {
      return appendPostFix(OLD.buildStarRankStarDailyKey(date));
    }

    @Override
    public String buildStarRankStarWeekKey(int year, int weekOfYear) {
      return appendPostFix(OLD.buildStarRankStarWeekKey(year, weekOfYear));
    }

    @Override
    public String buildStarRankStarWeekKey(LocalDate localDate) {
      return appendPostFix(OLD.buildStarRankStarWeekKey(localDate));
    }

    @Override
    public String buildStarLiveTotalRankMonthKey(int offset) {
      return appendPostFix(OLD.buildStarLiveTotalRankMonthKey(offset));
    }

    private String appendPostFix(String target) {
      return target + "_new_v2";
    }
  }
}
