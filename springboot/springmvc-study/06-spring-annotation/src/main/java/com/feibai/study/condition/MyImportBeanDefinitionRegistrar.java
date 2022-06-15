package com.feibai.study.condition;

import com.feibai.study.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  /**
   * AnnotationMetadata: 当前类的注解信息
   * BeanDefinitionRegistry: BeanDefinition-注册类
   * 把所有需要添加到容器中bean调用BeanDefinitionRegistry.registerBeanDefinition--手动注册进来
   */
  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    boolean definition = registry.containsBeanDefinition("com.feibai.study.bean.Red");
    boolean definition2 = registry.containsBeanDefinition("com.feibai.study.bean.Blue");
    if (definition && definition2) {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
      registry.registerBeanDefinition("rainBow", beanDefinition);
    }
  }

}
