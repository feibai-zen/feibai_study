package com.feibai.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.feibai.study.mapper")
@SpringBootApplication
public class SpringBootShardingJdbcApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootShardingJdbcApplication.class, args);
  }

}
