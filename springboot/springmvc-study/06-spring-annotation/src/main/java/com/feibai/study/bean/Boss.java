package com.feibai.study.bean;

import org.springframework.stereotype.Component;

//Ĭ�ϼ���ioc�����е��������������������޲ι��������������ٽ��г�ʼ����ֵ�Ȳ���
@Component
public class Boss {
	
	
	private Car car;
	
	//������Ҫ�õ���������Ǵ������л�ȡ
	public Boss(Car car){
		this.car = car;
		System.out.println("Boss...�вι�����");
	}
	
	

	public Car getCar() {
		return car;
	}


	//@Autowired 
	//��ע�ڷ�����Spring����������ǰ���󣬾ͻ���÷�������ɸ�ֵ��
	//����ʹ�õĲ������Զ������͵�ֵ��ioc�����л�ȡ
	public void setCar(Car car) {
		this.car = car;
	}



	@Override
	public String toString() {
		return "Boss [car=" + car + "]";
	}
	
	

}
