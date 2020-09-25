package com.feibai.study.demos.demos.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;


public final class TimeUtils {

  private TimeUtils() {
  }

  public static String combineSuffix(String origin, byte suffixType, LocalDateTime dateTime) {
    TimeSuffixEnum suffixEnum = TimeSuffixEnum.getEnum(suffixType);
    if (suffixEnum == null || suffixEnum == TimeSuffixEnum.NONE) {
      return origin;
    }
    return combineSuffix(origin, suffixEnum, dateTime);
  }

  public static String combineSuffix(String origin, byte suffixType, long timeMills) {
    TimeSuffixEnum suffixEnum = TimeSuffixEnum.getEnum(suffixType);
    if (suffixEnum == null || suffixEnum == TimeSuffixEnum.NONE) {
      return origin;
    }
    return combineSuffix(origin, suffixEnum,
            Instant.ofEpochMilli(timeMills).atZone(ZoneOffset.ofHours(8)).toLocalDateTime());
  }

  public static String combineSuffix(String origin, TimeSuffixEnum suffixEnum, LocalDateTime dateTime) {
    String suffix = getSuffix(suffixEnum, dateTime);
    if (suffix == null || suffix.isEmpty()) {
      return origin;
    }
    return origin + "_" + suffix;
  }

  public static String getSuffix(TimeSuffixEnum suffixEnum, LocalDateTime dateTime) {
    switch (suffixEnum) {
      case NONE:
        return "";
      case SECONDS:
        return String
                .format("%4d%02d%02d%02d%02d%02d", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(),
                        dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
      case MINUTES:
        return String
                .format("%4d%02d%02d%02d%02d", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(),
                        dateTime.getHour(), dateTime.getMinute());
      case HOURS:
        return String.format("%4d%02d%02d%02d", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(),
                dateTime.getHour());
      case DAYS:
        return String.format("%4d%02d%02d", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
      case DAYS_LINE:
        return String.format("%4d-%02d-%02d", dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
      case WEEKS:
        dateTime = dateTime.with(DayOfWeek.MONDAY);
        TemporalField weekBasedYear = WeekFields.of(DayOfWeek.MONDAY, 7).weekOfWeekBasedYear();
        return String.format("%4d%02d", dateTime.getYear(), dateTime.get(weekBasedYear));
      case MONTH:
        return String.format("%4d%02d", dateTime.getYear(), dateTime.getMonthValue());
      default:
        return null;
    }
  }

  public static void main(String[] args) {
    String zkey = TimeUtils.combineSuffix("act_20_06_c_guess_fans_hit", (byte) 5, LocalDateTime.now());
  }
}
