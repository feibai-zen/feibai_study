package com.my.study.demos.multithread.foundation.cooperation;

public class Cooperation_Buffer_Car {
	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		new Thread(new Productor_Car(buffer)).start();
		new Thread(new Consumer_Car(buffer)).start();
	}

}

class Productor_Car implements Runnable {
	private Buffer buffer;

	public Productor_Car(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(200);
				buffer.push(new Car(i));

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Consumer_Car implements Runnable {
	private Buffer buffer;

	public Consumer_Car(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(2000);
				buffer.pop();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}

class Buffer {
	int j = 0;
	Car[] carsArr = new Car[10];
	int count = 0;

	public synchronized Car pop() {
		Car car;
		try {
			if (count == 0) {
				wait();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		car = carsArr[--count];
		notifyAll();
		System.out.println("销售了" + j++ + "台车");
		return car;
	}

	public synchronized void push(Car car) {
		try {
			if (count >= 10) {
				wait();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		carsArr[count++] = car;
		notifyAll();
		System.out.println("生产了" + car.getId() + "台车");

	}
}

class Car {
	int id;
	String engine;

	public Car(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}