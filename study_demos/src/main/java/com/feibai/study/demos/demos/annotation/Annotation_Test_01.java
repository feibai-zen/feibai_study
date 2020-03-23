package com.feibai.study.demos.demos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author feibai
 *
 * @Time 2019年8月7日
 *
 */
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation_Test_01 {

	String studentName() default "";

	int age() default 0;

	int id() default -1; // String indexOf("abc") -1

	String[] schools() default { "清华大学", "北京大学" };

}
