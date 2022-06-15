package com.feibai.spring.study.service;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class ApplicationRunnerInit implements ApplicationRunner {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private final String LOG_PREFIX = ">>>>>>>>>>ApplicationRunner >>>>>>>>>> ";


  @Override
  public void run(ApplicationArguments args) throws Exception {
    try {
      this.logger.error("{} args:{}", LOG_PREFIX, JSONObject.toJSONString(args));
      for (String optionName : args.getOptionNames()) {
        this.logger.error("{} argName:{} argValue:{}", LOG_PREFIX, optionName, args.getOptionValues(optionName));
      }
      this.logger.error("{}", LOG_PREFIX);
    } catch (Exception e) {
      logger.error("CommandLineRunnerInit run failed", e);
    }
  }
}

