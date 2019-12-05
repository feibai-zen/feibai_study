package com.my.study.designpatterns.observer2;

import java.util.Observable;
import java.util.Observer;

public class ObserverA implements Observer {

	private int myState;

	@Override
	public void update(Observable o, Object arg) {
	}

	public int getMyState() {
		return myState;
	}

	public void setMyState(int myState) {
		this.myState = myState;
	}

}
