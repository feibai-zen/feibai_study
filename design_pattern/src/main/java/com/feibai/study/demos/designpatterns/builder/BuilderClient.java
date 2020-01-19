package com.feibai.study.demos.designpatterns.builder;

/**
 * 构建者模式：
 * 
 * 1）分离了对象子组件的单独构造（由Builder来负责）和装配（由Director负责）。从而可以构造出复杂的对象。
 * 该模式适用于：某个对象的构建过程复杂情况下使用。
 * 
 * 2）由于实现了构建与装配的解耦。不同的构建器，相同的装配，也可以做出不同的对象；相同的构建器，不同的装配顺序也可以
 * 做出不同的对象。这就实现了构建算法、装配算法的解耦，实现了更好的复用。
 * 
 * Director：组装零件、子组件
 * 
 * Builder：生产零件、子组件
 * 
 * @author feibai
 *
 */
public class BuilderClient {
	public static void main(String[] args) {

		AirShipDirector director = new AudiAirshipDirector(new AudiAirShipBuilder());

		AirShip ship = director.directAirShip();

		System.out.println(ship.getEngine().getName());

		ship.launch();

	}
}
