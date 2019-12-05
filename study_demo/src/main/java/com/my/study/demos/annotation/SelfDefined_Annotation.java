package com.my.study.demos.annotation;

/**
 * 
 * @author leeyuanlong
 *
 */
@Annotation_Test_01
public class SelfDefined_Annotation {

	@Annotation_Test_01(age = 19, studentName = "leeyuanlong", id = 1001, schools = { "西电", "农大" })
	public void test() {
	}

	@Annotation_Test_02("aaaa")
	public void test2() {
	}

}