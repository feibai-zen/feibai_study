package com.feibai.spring.study.job;

import com.feibai.spring.study.aspect.lock.ExecuteOnceRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CronTimeJob {

  /**
   * 每分钟
   */
  @Scheduled(cron = "0 */1 * * * ?")
  @ExecuteOnceRedisLock(key = "cron_time_job_one_minute")
  public void minute() {
    log.info("execute job per minute.");

  }

  /**
   * 每十分钟
   */
  @Scheduled(cron = "0 */10 * * * ?")
  @ExecuteOnceRedisLock(key = "cron_time_job_ten_minute")
  public void tenMinute() {
    log.info("execute job per 10 minutes.");
  }

  /**
   * 每半小时
   */
  @Scheduled(cron = "0 0/30 * * * ?")
  @ExecuteOnceRedisLock(key = "cron_time_job_half_hour")
  public void halfHour() {
    log.info("execute job per 30 minutes.");

  }

  /**
   * 每小时
   */
  @Scheduled(cron = "0 0 * * * ?")
  @ExecuteOnceRedisLock(key = "cron_time_job_hour")
  public void hour() {
    log.info("execute job per hour.");
  }

  /**
   * 每天0点
   */
  @Scheduled(cron = "0 0 0 * * ?")
  @ExecuteOnceRedisLock(key = "cron_time_job_day")
  public void day() {
  }

  /**
   * 每周一零点执行
   */
  @Scheduled(cron = "0 0 0 ? * MON")
  @ExecuteOnceRedisLock(key = "cron_time_job_week")
  public void week() {

  }

  public static void main(String[] args) {
    System.out.println(Long.valueOf(123).equals(123));
    System.out.println(String.valueOf("123").equals("123"));
    System.out.println("123".equals("123"));
    System.out.println(Integer.valueOf(123).equals(123));
  }
}
