package com.feibai.study.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//�ж��Ƿ�linuxϵͳ
public class LinuxCondition implements Condition {

	/**
	 * ConditionContext���ж�������ʹ�õ������ģ�������
	 * AnnotatedTypeMetadata��ע����Ϣ
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// TODO�Ƿ�linuxϵͳ
		//1���ܻ�ȡ��iocʹ�õ�beanfactory
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		//2����ȡ�������
		ClassLoader classLoader = context.getClassLoader();
		//3����ȡ��ǰ������Ϣ
		Environment environment = context.getEnvironment();
		//4����ȡ��bean�����ע����
		BeanDefinitionRegistry registry = context.getRegistry();
		
		String property = environment.getProperty("os.name");
		
		//�����ж������е�beanע�������Ҳ���Ը�������ע��bean
		boolean definition = registry.containsBeanDefinition("person");
		if(property.contains("linux")){
			return true;
		}
		
		return false;
	}

}
