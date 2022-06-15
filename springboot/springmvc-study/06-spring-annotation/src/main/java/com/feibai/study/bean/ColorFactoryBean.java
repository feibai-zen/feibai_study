package com.feibai.study.bean;

import org.springframework.beans.factory.FactoryBean;

public class ColorFactoryBean implements FactoryBean<Color> {

	//返回一个Color对象，这个对象会添加到容器中
	@Override
	public Color getObject() throws Exception {
		System.out.println("ColorFactoryBean...getObject...");
		return new Color();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Color.class;
	}

	//来控制是否单例
	//true：这个bean是单实例，在容器中会保留一份
	//false：这个bean是多实例的，每次获取都会创建一个新的bean
	@Override
	public boolean isSingleton() {
		return false;
	}
}
