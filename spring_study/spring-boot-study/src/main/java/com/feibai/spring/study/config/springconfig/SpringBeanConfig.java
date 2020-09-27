package com.feibai.spring.study.config.springconfig;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class SpringBeanConfig {

  @Bean(name = "restTemplate")
  public RestTemplate acquireRestTemplate() {
    return new RestTemplate();
  }

  @Bean(name = "messageSource")
  public ResourceBundleMessageSource acquireMessageSource() {
    ResourceBundleMessageSource responseReturnValueHandler = new ResourceBundleMessageSource();
    return responseReturnValueHandler;
  }

  /**
   * 配置文件上传
   */
  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver getCommonsMultipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(20971520);
    multipartResolver.setMaxInMemorySize(1048576);
    return multipartResolver;
  }

  @Bean
  public ServletRegistrationBean getDruidStatView() {
    ServletRegistrationBean druidStatView = new ServletRegistrationBean(new StatViewServlet());
    druidStatView.addUrlMappings("/druid/*");
    return druidStatView;
  }

}
