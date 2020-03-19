package com.feibai.designpatterns.study.prototype;

import java.io.Serializable;
import java.util.Date;

public class SheepWhihLowCopy implements Cloneable, Serializable { // 1997,英国的克隆羊，多利！

	private static final long serialVersionUID = 1L;
	private String sname;
	private Date birthday;

	public SheepWhihLowCopy(String sname, Date birthday) {
		super();
		this.sname = sname;
		this.birthday = birthday;
	}

	public SheepWhihLowCopy() {
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object obj = super.clone(); // 直接调用object对象的clone()方法！
		return obj;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}

//测试深复制
class SheepWhitDeepCLone implements Cloneable { // 1997,英国的克隆羊，多利！
	private String sname;
	private Date birthday;

	public SheepWhitDeepCLone(String sname, Date birthday) {
		super();
		this.sname = sname;
		this.birthday = birthday;
	}

	public SheepWhitDeepCLone() {
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object obj = super.clone(); // 直接调用object对象的clone()方法！

		// 添加如下代码实现深复制(deep Clone)
		SheepWhitDeepCLone s = (SheepWhitDeepCLone) obj;
		s.birthday = (Date) this.birthday.clone(); // 把属性也进行克隆！

		System.out.println("S: " + s);
		System.out.println("Obj: " + obj);
		return obj;// 这里返回s与返回obj是一样的
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
