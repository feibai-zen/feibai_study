package com.feibai.study.caffeine.config;

public enum CacheTypeEnum {
  TEN(10),

  FIVE(5);

  private final int expires;

  CacheTypeEnum(int expires) {
    this.expires = expires;
  }

  public int getExpires() {
    return expires;
  }
}
