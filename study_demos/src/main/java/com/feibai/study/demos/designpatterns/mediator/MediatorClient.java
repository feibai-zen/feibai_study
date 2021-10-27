package com.feibai.study.demos.designpatterns.mediator;

public class MediatorClient {
	public static void main(String[] args) {
		Mediator m = new President();

		Market market = new Market(m);
		Development devp = new Development(m);
		Finacial f = new Finacial(m);

		market.selfAction();
		market.outAction();

	}
}
