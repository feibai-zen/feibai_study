package com.feibai.study.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * postProcessBeforeInitialization：在初始化之前工作
 * postProcessAfterInitialization：在初始化之后工作
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    // TODO Auto-generated method stub
    System.out.println("postProcessBeforeInitialization..." + beanName + "=>" + bean);
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    // TODO Auto-generated method stub
    System.out.println("postProcessAfterInitialization..." + beanName + "=>" + bean);
    return bean;
  }

}
