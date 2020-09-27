package com.feibai.spring.study.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 配置定时任务多线程执行
 * 不配置的话定时任务是单线程执行的
 */
@Configuration
public class ScheduledThreadPoolConfig implements SchedulingConfigurer {
  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setScheduler(
            new ScheduledThreadPoolExecutor(4, new BasicThreadFactory.Builder().namingPattern("crab-scheduled-%d").daemon(true).build()));
  }
}
