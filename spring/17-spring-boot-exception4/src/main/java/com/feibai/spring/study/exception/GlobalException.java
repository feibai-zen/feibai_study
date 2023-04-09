package com.feibai.spring.study.exception;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 通过SimpleMappingExceptionResolver做全局异常处理
 * <p>
 * 优点：可以在一个方法中添加所有的异常处理方式
 * <p>
 * 缺点：无法在视图跳转的时候向页面传递异常对象
 */
@Configuration//springboot会对里面所有的@Bean创建对象
public class GlobalException {

  /**
   * 该方法必须要有返回值。返回值类型必须是：SimpleMappingExceptionResolver
   */
  @Bean
  public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
    SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

    Properties mappings = new Properties();

    /**
     * 参数一：异常的类型，注意必须是异常类型的全名
     * 参数二：视图名称
     */
    mappings.put("java.lang.ArithmeticException", "error1");
    mappings.put("java.lang.NullPointerException", "error2");

    //设置异常与视图映射信息的
    resolver.setExceptionMappings(mappings);

    return resolver;
  }

}
