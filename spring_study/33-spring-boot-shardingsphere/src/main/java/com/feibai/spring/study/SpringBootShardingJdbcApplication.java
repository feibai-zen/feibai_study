package com.feibai.spring.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * https://www.jianshu.com/p/952108f777a3
 * <p>
 * 也可以参考以上demo
 */

@MapperScan("com.feibai.study.mapper")
@SpringBootApplication
public class SpringBootShardingJdbcApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootShardingJdbcApplication.class, args);
  }

}
