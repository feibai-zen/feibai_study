package com.my.study.demos.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 
 * @author leeyuanlong
 *
 * @Time 2019年8月7日
 *
 */
public class InterpreteAnnotation {

	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("com.my.study.demos.annotation.Student");

			// 获得类中所有有效注解
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation a : annotations) {
				System.out.println(a);
			}
			// 获得类的指定注解
			Table st = (Table) clazz.getAnnotation(Table.class);
			System.out.println(st.value());

			// 获得类的属性的注解
			Field f = clazz.getDeclaredField("studentName");
			SxtField sxtField = f.getAnnotation(SxtField.class);
			System.out.println(sxtField.columnName() + "--" + sxtField.type() + "--" + sxtField.length());

			// 根据获得的表名,字段的信息,拼出DDL语句，然后使用JDBC执行这个SQL，在数据库中生成相应的表

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
