package com.feibai.spring.study.demos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * 每日签到
 */

public class SignIn {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  private String zBaseKey;
  private Integer limit = 7;
  private String resKey = "signInInfo";
  private Object key;//uid、anchorUid、buyerId

  private static final String LOCK_BASE_KEY = "crab_business_distribution_sign_in_lock_key";

  public SignInfo getSignInCnt(Long uid, LocalDateTime dateTime) {
    LocalDateTime lastDateTime = dateTime.minusDays(1);
    String today = String.format("%4d%2d%2d", dateTime.getDayOfYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
    String lastDay = String.format("%4d%2d%2d", lastDateTime.getDayOfYear(), lastDateTime.getMonth(), lastDateTime.getDayOfMonth());

    SignInfo signInfo = new SignInfo();

    //分布式锁
    Boolean lock = redisTemplate.opsForValue().setIfAbsent(LOCK_BASE_KEY + uid, "lock", 200, TimeUnit.MILLISECONDS);
    if (!Objects.isNull(lock) && lock) {
      try {
        Double scoreToday = redisTemplate.opsForZSet().score(zBaseKey + today, String.valueOf(uid));
        if (!Objects.isNull(scoreToday)) {
          //今日已经打过卡
          signInfo.setRetCode(-1);
          signInfo.setSignCnt(scoreToday.intValue());
          signInfo.setMsg("您今日已打过卡");
          return signInfo;
        }
        Double scoreLastDay = redisTemplate.opsForZSet().score(zBaseKey + lastDay, String.valueOf(uid));
        if (Objects.isNull(scoreLastDay)) {
          //昨天未打卡
          redisTemplate.opsForZSet().add(zBaseKey + today, String.valueOf(uid), 1);
          signInfo.setRetCode(0);
          signInfo.setSignCnt(1);
        } else if (scoreLastDay.intValue() >= limit) {//打卡次数超限
          signInfo.setRetCode(-1);
          signInfo.setSignCnt(limit);
          signInfo.setMsg(String.format("您已经完成%s次打卡任务", limit));
          redisTemplate.opsForZSet().add(zBaseKey + today, String.valueOf(uid), limit);
        } else {
          redisTemplate.opsForZSet().add(zBaseKey + today, String.valueOf(uid), scoreLastDay + 1);
          signInfo.setRetCode(0);
          signInfo.setSignCnt(scoreLastDay.intValue() + 1);
        }
      } catch (Exception e) {
      } finally {
        redisTemplate.delete(LOCK_BASE_KEY + uid);
      }

    } else {
      signInfo.setRetCode(-1);
      signInfo.setMsg("您打卡速度太快咯...");
    }
    return signInfo;
  }

  class SignInfo {
    private int retCode;//-1:打卡失败（打卡超出限制、今日已打卡） 0:打卡成功
    private int signCnt;
    private String msg;

    public int getRetCode() {
      return retCode;
    }

    public void setRetCode(int retCode) {
      this.retCode = retCode;
    }

    public int getSignCnt() {
      return signCnt;
    }

    public void setSignCnt(int signCnt) {
      this.signCnt = signCnt;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }
  }
}