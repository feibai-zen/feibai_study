package com.feibai.study.demos.demos.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectDemo {
	public static void main(String[] args) throws Exception {
		// 加载类
		Class<?> class0 = ReflectDemo.class.getClassLoader().loadClass("com.feibai.study.demos.demos.reflect.Person");
		Class<?> class1 = Class.forName("com.feibai.study.demos.demos.reflect.Person1");
		Class<?> class11 = Class.forName("com.feibai.study.demos.demos.reflect.Person");
		Class<?> class2 = Person.class;
//获取构造器
		Constructor<?>[] constructor = class11.getDeclaredConstructors();
		System.out.println("构造器：");
		for (int i = 0; i < constructor.length; i++) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("\t");
			buffer.append(i + 1);
			buffer.append(":");
			// 获取修饰符
			buffer.append(Modifier.toString(constructor[i].getModifiers()));
			buffer.append(" ");
			// 获取构造器的名称
			buffer.append(constructor[i].getName());
			buffer.append(" （");
			// 获取形参列表
			Class<?>[] s1 = constructor[i].getParameterTypes();
			for (int j = 0; j < s1.length; j++) {
				buffer.append(s1[j].getSimpleName());
				buffer.append(" ");
			}
			buffer.append(" ）");
			System.out.println(buffer.toString());
		}
		// 获取方法
		Method[] methods = class1.getDeclaredMethods();
		for (Method method : methods) {
			StringBuilder builder = new StringBuilder();
			// 修饰符
			builder.append("\t" + Modifier.toString(method.getModifiers()));
			// 返回值
			Class<?> returntype = method.getReturnType();
			builder.append(" " + returntype.getSimpleName());
			// 方法名称
			builder.append(" " + method.getName());
			// 形参列表类型
			builder.append("(");
			Class<?>[] types = method.getParameterTypes();
			for (Class<?> typess : types) {
				builder.append(typess.getSimpleName());
			}
			builder.append(")");
			System.out.println(builder.toString());
		}
		// 获取成员变量
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			StringBuilder builder = new StringBuilder();
			// 修饰符
			builder.append("\t" + Modifier.toString(field.getModifiers()));
			// 名称
			builder.append(" " + field.getName());
			// 类型
			builder.append(" " + field.getType().getSimpleName());
			System.out.println(builder.toString());
		}
		// 通过反射机制调用构造器实例化一个类的对象
		/*
		 * 1．构造器实例化一个类的对象 2. 调用getDeclaredConstructor或者getConstructor(只能访问类中声明为公有的构造器)
		 * 参数 ：[参数类型1,参数类型2,...] 3.直接访问的构造器不一定是私有，设置允许访问onstructor. setAccessible(true),
		 * getConstructor 只能调用公有的
		 */
		Constructor<?> constructors = class1.getConstructor(String.class, int.class);
		Person1 persons = (Person1) constructors.newInstance("朱丽叶", 18);
		System.out.println(persons.getName() + " " + persons.getAge());
		// getDeclaredConstructor 调用私有的
		Constructor<?> constructor2 = class1.getDeclaredConstructor();
		constructor2.setAccessible(true);// 打破封装
		Person1 personss = (Person1) constructor2.newInstance();
		personss.setName("小龙女");
		personss.setAge(19);
		System.out.println(personss.getName() + " " + personss.getAge());
		// 通过反射机制调用普通方法
		/*
		 * 普通方法: 1．调用getDeclaredMethod 或者 getMethod(只能访问类中声明为公有的方法) 参数 ：方法名,
		 * [参数类型1,参数类型2,...] 2. 使用反射机制可以打破封装性，导致了 java 对象的属性不安全。 3.
		 * 无法直接访问的方法不一定是私有,设置允许访问 method.setAccessible(true)
		 * method.invoke(对象,[参数1,参数2,....] );
		 */
		Method method = class1.getMethod("say", String.class);
		String string = (String) method.invoke(personss, "可以访问的");// 第一个方法里面必须是一个对象，后面是参数值列表
		System.out.println(string);
		// 调用无法直接访问的方法
		Method method2 = class1.getDeclaredMethod("test");
		method2.setAccessible(true);
		method2.invoke(personss);
		// 通过反射机制获取或者设置成员变量
		/*
		 * 成员变量: 1.调用getDeclaredField （私有的） 或者 getField(只能访问类中声明为公有的成员变量) 参数 ：变量名称
		 * 2.无法直接访问的成员变量不一定是私有, field.setAccessible(true); 取值 field.get(对象) 赋值
		 * field.set(对象,值)
		 */
		// 设置值
		Field field = class1.getDeclaredField("name");
		field.setAccessible(true);
		field.set(personss, "小明");
		// 获取值
		System.out.println(field.get(personss));
		System.out.println(personss.say("要去蹦极"));
	}
}