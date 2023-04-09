package com.feibai.spring.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class FeibaiStudyStartup extends SpringBootServletInitializer implements CommandLineRunner {
  @Autowired
  private ApplicationContext appContext;

  private static final Logger logger = LoggerFactory.getLogger(FeibaiStudyStartup.class);

  public static void main(String[] args) {
    logger.info("===============start spring-boot application.");
    logger.trace("=============trace");
    SpringApplication.run(FeibaiStudyStartup.class, args);
  }

  /**
   * 外置tomcat启动调用
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

    return application.sources(FeibaiStudyStartup.class);
  }

  @Override
  public void run(String... args) throws Exception {
    String[] beans = appContext.getBeanDefinitionNames();
    Arrays.sort(beans);
    for (String bean : beans) {
      System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
    }
  }

}
