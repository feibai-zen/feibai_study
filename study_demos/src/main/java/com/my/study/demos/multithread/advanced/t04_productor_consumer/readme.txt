练习（生产者消费者模式）：
自定义同步容器，容器容量上限为10。可以在多线程中应用，并保证数据线程安全。

Object o = new Object();
public void m(){
	o = new Object();
	synchronized(o){
	}
}