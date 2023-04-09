package com.feibai.study.demos.demos.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Date;

public class TimeTestJdk8 {
    //测试LocalDateTime
    @Test
    public void getTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        System.out.println(now.getYear());
        System.out.println(now.getMonth());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfYear());
        System.out.println(now.getDayOfWeek());
        System.out.println(now.getMonthValue());
        System.out.println(now.getHour());
        System.out.println(now.getLong(ChronoField.YEAR));
        System.out.println(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Test
    public void testLocalDateTime() {
        LocalDate localDate = LocalDate.now().plusMonths(1);
    }

    @Test
    public void today() {
        LocalDate today = LocalDate.now();//获取当前日期，不含时间
        System.out.println("今天的日期:" + today);
    }

    @Test
    public void year_month_date() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.println("year:" + year);
        System.out.println("month:" + month);
        System.out.println("day:" + day);
    }

    @Test
    public void self_define() {
        LocalDate date = LocalDate.of(2018, 2, 6);
        System.out.println("自定义日期:" + date);
    }

    @Test
    public void judge_equal() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018, 2, 5);

        if (date1.equals(date2)) {
            System.out.println("时间相等");
        } else {
            System.out.println("时间不等");
        }
    }

    // 忽略年
    @Test
    public void cycle_time() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018, 2, 6);

        MonthDay birthday = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(date1);

        if (currentMonthDay.equals(birthday)) {
            System.out.println("是你的生日");
        } else {
            System.out.println("你的生日还没有到");
        }
    }

    @Test
    public void currentTime() {
        LocalTime time = LocalTime.now();//当前时间，不含有日期信息
        System.out.println("获取当前的时间,不含有日期:" + time);
    }

    // 时间增加
    @Test
    public void plusTime() {
        LocalTime time = LocalTime.now();
        //方法返回一个全新的LocalTime实例，由于其不可变性，返回后一定要用变量赋值。
        LocalTime newTime = time.plusHours(3);
        System.out.println("三个小时后的时间为:" + newTime);
    }

    // 计算一周后的日期
    @Test
    public void one_week_after() {
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期为:" + today);
        //plus()方法用来增加天、周、月，ChronoUnit类声明了这些时间单位。由于LocalDate也是不变类型，返回后一定要用变量赋值。
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期为:" + nextWeek);
    }

    // 一年后的日期
    @Test
    public void one_year_before() {
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期 : " + previousYear);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期:" + nextYear);
    }

    // 获取当时的时间戳，或当前时区下的日期时间信息
    @Test
    public void unix_time() {
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());

        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());
    }

    @Test
    public void compareDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2018, 2, 6);
        if (tomorrow.isAfter(today)) {
            System.out.println("之后的日期:" + tomorrow);
        }
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(today)) {
            System.out.println("之前的日期:" + yesterday);
        }
    }

    //把本时区的时间转换成另一个时区的时间
    @Test
    public void timeZone() {
        // Date and time with timezone in Java 8
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
    }

    // 检查闰年
    @Test
    public void isLeapYear() {
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println("2018 is not a Leap year");
        }
    }

    // 忽略月--到期日
    // MonthDay检查重复事件的例子相似，YearMonth是另一个组合类，用于表示信用卡到期日、FD到期日、期货期权到期日等。还可以用这个类得到 当月
    // 共有多少天，YearMonth实例的lengthOfMonth()方法可以返回当月的天数，在判断2月有28天还是29天时非常有用。
    @Test
    public void year_month() {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }

    // 两个日期之间的天数和月数
    @Test
    public void days_between_two_date() {
        LocalDate today = LocalDate.now();
        LocalDate java8Release = LocalDate.of(2018, 12, 14);

        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : "
                + periodToNextJavaRelease.getMonths());
    }

    // 获取当前的时间戳
    @Test
    public void time_stamp() {
        //时间戳信息里同时包含了日期和时间，这和java.util.Date很像。实际上Instant类确实等同于 Java 8之前的Date类，你可以使用Date类和
        //Instant类各自的转换方法互相转换，例如：Date.from(Instant) 将Instant转换成java.util.Date，Date.toInstant()则是将Date类
        //转换成Instant类。
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());
    }

    // 字符串互转日期类型
    @Test
    public void stringToDateTime() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串:" + str);

        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate date2 = LocalDate.parse(str, format2);
        System.out.println("日期类型:" + date2);
    }

    // 使用预定义的格式化工具去解析或格式化日期
    @Test
    public void format_date() {
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow + "  格式化后的日期为:  " + formatted);
    }

    @Test
    public void other(long time) {
        System.out.println(Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDate());
    }

    @Test
    public void isOnDuration() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(2019, 1, 25, 12, 0);
        LocalDateTime end = LocalDateTime.of(2019, 1, 30, 0, 0);
        System.out.println(now.isAfter(start) && now.isBefore(end));
    }

    @Test
    public void test_convert(String[] args) {

        String nowDateStr = LocalDate.now().toString();
        System.out.println(nowDateStr);//2018-03-27

        LocalDate nowDate = LocalDate.parse("2018-03-25");
        System.out.println(nowDate.toString());//2018-03-25


        String nowTimeStr = LocalTime.now().toString();
        System.out.println(nowTimeStr);//13:45:07.105

        LocalTime nowTime = LocalTime.parse("12:10:13");
        System.out.println(nowTime.toString());//12:10:13

        System.out.println(LocalDateTime.now().toString());//2018-03-27T13:55:34.047
        System.out.println(LocalDateTime.now().toLocalDate().toString());//2018-03-27
        System.out.println(LocalDateTime.now().toLocalTime().toString());//13:55:34.047

        System.out.println(LocalDateTime.MAX.toString());//+999999999-12-31T23:59:59.999999999
        System.out.println(LocalDateTime.MIN.toString());//-999999999-01-01T00:00

        date2LocalDateTime(new Date());
        localDateTime2Date(LocalDateTime.now());
    }

    /**
     * Date转换为LocalDateTime
     */
    @Test
    public void date2LocalDateTime(Date date) {
        LocalDateTime startDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneOffset.ofHours(8))
                .toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     */
    @Test
    public void localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        System.out.println(date.toString());
    }

    public Duration getDuration() {
        Duration duration = Duration.between(LocalDateTime.now(), LocalDate.now().plusDays(1).atStartOfDay());

        duration.getSeconds();
        return duration;
    }

    public static String buildFansLiveTotalRankDailyKey(int offset) {
        LocalDate localDate = LocalDate.now().plusDays(offset);

        return "gift_fans_live_total_rank_daily_" + localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String buildFansLiveTotalRankWeekKey(int offset) {
        LocalDate localDate = LocalDate.now().plusWeeks(offset);
        TemporalField weekBasedYear = WeekFields.of(DayOfWeek.MONDAY, 7).weekOfWeekBasedYear();
        LocalDate formatLocalDate = localDate.minusDays((long) (localDate.getDayOfWeek().getValue() - 1));
        StringBuilder sb = new StringBuilder();

        sb.append("gift_fans_live_total_rank_week_").append(String.format("%4d%02d", formatLocalDate.getYear(), formatLocalDate.get(weekBasedYear)));

        return sb.toString();
    }

    public static String buildFansLiveTotalRankMonthKey(int offset) {
        LocalDate localDate = LocalDate.now().plusDays(offset);
        StringBuilder sb = new StringBuilder();
        sb.append("gift_fans_live_total_rank_month_").append(localDate.getYear())
                .append(localDate.getMonth().toString());

        return sb.toString();
    }

    /**
     * 时间间隔
     */
    public Duration getDuration(int scene) {
        Duration duration = null;
        switch (scene) {
            case 1:
                duration = Duration.between(LocalDateTime.now(), LocalDate.now()
                        .plusDays(1).atStartOfDay());
                break;
            case 2:
                duration = Duration.between(LocalDateTime.now(), LocalDate.now()
                        .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atStartOfDay());
                break;
            case 3:
                duration = Duration.between(LocalDateTime.now(), LocalDate.now()
                        .with(TemporalAdjusters.firstDayOfNextMonth()).atStartOfDay());
                break;
            default:
                throw new RuntimeException("params error");
        }
        return duration;
    }

    public long milisecondsRemain() {
        String deadlineTime = "2020-08-12 12:00:00";
        long unixTimeStamp = 157800000;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Shanghai"));
        LocalDateTime start = LocalDateTime.parse(deadlineTime, format);
        LocalDateTime currentDateTime = Instant.ofEpochMilli(unixTimeStamp).atZone(ZoneOffset.ofHours(8))
                .toLocalDateTime();
        long remainSec = Duration.between(currentDateTime, start).toMillis();

        return remainSec < 0 ? 0L : remainSec;
    }

}
