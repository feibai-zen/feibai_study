package com.feibai.spring.study.multithread.advanced.t09_semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ObjectCache<T> {
  //对象工厂
  public interface ObjectFactory<T> {
    T makeObject();
  }

  //将对象封装节点中，放到一个先进先出的队列中，即对象池
  class Node {
    T obj;
    Node next;
  }

  final int capacity;//线程次容量
  final ObjectFactory<T> factory;
  final Lock lock = new ReentrantLock();//保证对象获取，释放的线程安全
  final Semaphore semaphore;//信号量
  private Node head;
  private Node tail;

  public ObjectCache(int capacity, ObjectFactory<T> factory) {
    this.capacity = capacity;
    this.factory = factory;
    this.semaphore = new Semaphore(this.capacity);
    this.head = null;
    this.tail = null;
  }

  /**
   * 从对象池中，获取对象
   */
  public T getObject() throws InterruptedException {
    semaphore.acquire();
    return getObjectFromPool();
  }

  /**
   * 将对象放回对象池
   */
  public void putBackObject(T t) {
    putBackObjectToPool(t);
    semaphore.release();
  }

  /**
   * 线程安全地从对象池获取对象
   */
  private T getObjectFromPool() {
    lock.lock();
    try {
      if (head == null) {
        return factory.makeObject();
      } else {
        Node ret = head;
        head = head.next;
        if (head == null)
          tail = null;
        ret.next = null;// help GC
        return ret.obj;
      }
    } finally {
      lock.unlock();
    }
  }

  /**
   * 线程安全地，将对象放回对象池
   */
  private void putBackObjectToPool(T t) {
    lock.lock();
    try {
      Node node = new Node();
      node.obj = t;
      if (tail == null) {
        head = tail = node;
      } else {
        tail.next = node;
        tail = node;
      }
    } finally {
      lock.unlock();
    }
  }

  /**
   * ObjectCache描述了一个基于信号量Semaphore的对象池实现。此对象池最多支持capacity个对象，这在构造函数中传入。对象池有一个基于FIFO的队列，
   * 每次从对象池的头结点开始取对象，如果头结点为空就直接构造一个新的对象返回。否则将头结点对象取出，并且头结点往后移动。特别要说明的如果对象的个
   * 数用完了，那么新的线程将被阻塞，直到有对象被返回回来。返还对象时将对象加入FIFO的尾节点并且释放一个空闲的信号量，表示对象池中增加一个可用对象。
   * 实际上对象池、线程池的原理大致上就是这样的，只不过真正的对象池、线程池要处理比较复杂的逻辑，所以实现起来还需要做很多的工作，例如超时机制，自动
   * 回收机制，对象的有效期等等问题。这里特别说明的是信号量只是在信号不够的时候挂起线程，但是并不能保证信号量足够的时候获取对象和返还对象是线程安全
   * 的，所以在示例中仍然需要锁Lock来保证并发的正确性。将信号量初始化为1，使得它在使用时最多只有一个可用的许可，从而可用作一个相互排斥的锁。这通常
   * 也称为二进制信号量，因为它只能有两种状态：一个可用的许可，或零个可用的许可。按此方式使用时，二进制信号量具有某种属性（与很多Lock 实现不同），
   * 即可以由线程释放“锁”，而不是由所有者（因为信号量没有所有权的概念）。在某些专门的上下文（如死锁恢复）中这会很有用。上面这段话的意思是说当某个线
   * 程A持有信号量数为1的信号量时，其它线程只能等待此线程释放资源才能继续，这时候持有信号量的线程A就相当于持有了“锁”，其它线程的继续就需要这把锁，
   * 于是线程A的释放才能决定其它线程的运行，相当于扮演了“锁”的角色。另外同公平锁非公平锁一样，信号量也有公平性。如果一个信号量是公平的表示线程在获
   * 取信号量时按FIFO的顺序得到许可，也就是按照请求的顺序得到释放。这里特别说明的是：所谓请求的顺序是指在请求信号量而进入FIFO队列的顺序，
   * 有可能某个线程先请求信号而后进去请求队列，那么次线程获取信号量的顺序就会晚于其后请求但是先进入请求队列的线程。这个在公平锁和非公平锁中谈过很多。
   */

}