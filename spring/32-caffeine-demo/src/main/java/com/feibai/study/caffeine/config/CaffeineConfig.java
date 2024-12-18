package com.feibai.study.caffeine.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    List<CaffeineCache> caffeineCaches = new ArrayList<>();
    for (CacheTypeEnum type : CacheTypeEnum.values()) {
      caffeineCaches.add(new CaffeineCache(type.name(),
          Caffeine.newBuilder().expireAfterWrite(type.getExpires(), TimeUnit.SECONDS)
              .build()));
    }

    cacheManager.setCaches(caffeineCaches);
    return cacheManager;
  }

}
