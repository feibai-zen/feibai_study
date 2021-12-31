package com.feibai.study.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ApplicationTests {

  @Test
  void contextLoads() {
  }


  @Test
  public void testDateFormat() {
    LocalDateTime ldt = LocalDateTime.now();


  }

}