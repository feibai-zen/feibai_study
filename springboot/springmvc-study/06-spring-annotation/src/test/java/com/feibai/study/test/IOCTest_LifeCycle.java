package com.feibai.study.test;

import com.feibai.study.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_LifeCycle {
	
	@Test
	public void test01(){
		//1������ioc����
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
		System.out.println("�����������...");
		
		//applicationContext.getBean("car");
		//�ر�����
		applicationContext.close();
	}

}
