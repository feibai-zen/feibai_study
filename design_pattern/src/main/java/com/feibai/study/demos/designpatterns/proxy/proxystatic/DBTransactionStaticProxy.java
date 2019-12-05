package com.feibai.study.demos.designpatterns.proxy.proxystatic;

/**
 * 静态代理模式-static proxy
 * 
 * @author leeyuanlong
 *
 */
public class DBTransactionStaticProxy {
	public static void main(String[] args) {
		PersonService personService = (PersonService) new PersonServiceProxy(new PersonServiceImpl(),
				new Transaction());
		personService.savePerson();
	}

}

interface PersonService {
	public void savePerson();

	public void updatePerson();

	public void deletePerson();

}

class Transaction {
	public void beginTransaction() {
		System.out.println("开启事务 ");
	}

	public void commit() {
		System.out.println("提交事务");
	}
}

class PersonServiceProxy implements PersonService {
	// 目标类
	private PersonService personService;
	// 增强类
	private Transaction transaction;

	// 利用构造函数将目标类和增强类注入
	public PersonServiceProxy(PersonService personService, Transaction transaction) {
		this.personService = personService;
		this.transaction = transaction;
	}

	@Override
	public void savePerson() {
		transaction.beginTransaction();
		personService.savePerson();
		transaction.commit();
	}

	@Override
	public void updatePerson() {
		transaction.beginTransaction();
		personService.updatePerson();
		transaction.commit();
	}

	@Override
	public void deletePerson() {
		transaction.beginTransaction();
		personService.deletePerson();
		transaction.commit();
	}

}

class PersonServiceImpl implements PersonService {
	@Override
	public void savePerson() {
		System.out.println("添加");
	}

	@Override
	public void updatePerson() {
		System.out.println("修改");
	}

	@Override
	public void deletePerson() {
		System.out.println("删除");
	}

}
/*
 * 以常见的增删改查为例，在执行增删改的时候我们需要开启事务，执行完成后需要提交事务,假如Service里有100个增删改的方法，
 * 那这100个方法里除了你自己真正需要处理
 * 的业务逻辑外，每个方法都还需要去关注开启事务，提交事务这些动作。那有没有稍微好点的办法解决呢？于是就出现了代理模式的概念，那什么是代理模式呢？
 * 1、什么是代 理模式
 * 简单来说：代理就是对目标方法进行增强。什么意思呢？还是上边的例子，Service里你的业务逻辑我们称之为需要执行的目标方法；开启事务，提交事务这些我们就
 * 可以称之为对目标方法的增强。于是乎，我们可不可以这样：“需要执行的目标”单独写一个类（目标类），“需要增强的动作”单独写一个类（增强类），最后再写一个类（
 * 代理
 * 类），把它两者结合到一起。这样的话，是不是就实现了开启事务，提交事务这些动作只需要在增强类里写一遍，然后我们在业务目标类里就只关心我们的业务动作就行了，其
 * 他的乱七八糟的比如开启事务、提交事务（除了这些还可以有别的一些动作，统一的日志操作等等）等等这些增强的动作我都不关注了，在业务类里只写业务！最后在代理类里
 * 把增强的动作和目标动作结合起来就ok了，然后用的时候，用咱们的代理对象就好了。这样做就实现了在业务类里可以只专注于业务，其他的不管，
 * 而且到达了松耦合的效果（ 就是那些增强的动作，比如事务，统一的日志操作等等，这些动作和我的业务是分离开的）。
 * 
 * 缺点： 1、假设一个系统中有100个Service，则需要创建100个代理对象
 * 2、如果一个Service中有很多方法需要事务（增强动作），发现代理对象的方法中还是有很多重复的代码 3、由第一点和第二点可以得出：静态代理的重用性不强
 * 那怎么解决呢？ 用动态代理就可以很好的解决上述问题
 */
