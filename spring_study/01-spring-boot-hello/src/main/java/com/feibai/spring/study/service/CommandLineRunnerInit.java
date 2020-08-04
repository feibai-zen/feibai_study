package com.feibai.spring.study.service;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class CommandLineRunnerInit implements CommandLineRunner {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private final String LOG_PREFIX = ">>>>>>>>>>CommandLineRunner >>>>>>>>>> ";

  @Override
  public void run(String... args) throws Exception {
    try {
      this.logger.error("{} args:{}", LOG_PREFIX, StringUtils.join(args, ","));
    } catch (Exception e) {
      logger.error("CommandLineRunnerInit run failed", e);
    }
  }
}

