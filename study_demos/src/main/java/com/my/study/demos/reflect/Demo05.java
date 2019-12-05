package com.my.study.demos.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.my.study.demos.reflect.annotation.SxtField;
import com.my.study.demos.reflect.annotation.SxtTable;

/**
 * 通过反射获取注解信息
 * 
 * @author leeyuanlong
 *
 * @Time 2019年8月7日
 *
 */
public class Demo05 {
	public static void main(String[] args) {

		try {
			Class clazz = Class.forName("com.bjsxt.test.annotation.SxtStudent");

			// 获得类的所有有效注解
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation a : annotations) {
				System.out.println(a);
			}
			// 获得类的指定的注解
			SxtTable st = (SxtTable) clazz.getAnnotation(SxtTable.class);
			System.out.println(st.value());

			// 获得类的属性的注解
			Field f = clazz.getDeclaredField("studentName");
			SxtField sxtField = f.getAnnotation(SxtField.class);
			System.out.println(sxtField.columnName() + "--" + sxtField.type() + "--" + sxtField.length());

			// 根据获得的表名、字段的信息，拼出DDL语句，然后，使用JDBC执行这个SQL，在数据库中生成相关的表

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
