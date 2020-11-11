package com.feibai.spring.study.advanced;

import com.feibai.spring.study.utils.applicationcontext.BaikalBeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 使用AutowireCapableBeanFactory实现
 */
@Component("baikalBeanUtils")
public class BaikalSpringBeanFactory implements BaikalBeanUtils.BaikalBeanFactory, ApplicationContextAware {

  private AutowireCapableBeanFactory beanFactory;

  /**
   * 注入Bean
   *
   * @param existingBean
   */
  @Override
  public void autowireBean(Object existingBean) {
    this.beanFactory.autowireBean(existingBean);
  }

  /**
   * 检查是否有此Bean
   *
   * @param name
   */
  @Override
  public boolean containsBean(String name) {
    return this.beanFactory.containsBean(name);
  }

  /**
   * @param applicationContext
   * @throws BeansException
   */
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.beanFactory = applicationContext.getAutowireCapableBeanFactory();

    /**将初始化完的beanFactory塞入BaikalBeanUtils*/
    BaikalBeanUtils.setFactory(this);
  }
}
