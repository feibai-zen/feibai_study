package com.feibai.study.demos.multithread.advanced.t06_container;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test_09_BlockedQueueWithReentrantLock<T> {
    final ArrayQueue<T> queue = new ArrayQueue<T>(10);
    final Lock lock = new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty = lock.newCondition();

    // 入队
    void enq(T element) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= 10) {
                // 等待队列不满
                notFull.await();
            }
            // 省略入队操作...
            //入队后,通知可出队
            queue.add(element);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 出队
    T deq() throws InterruptedException {
        lock.lock();
        T result = null;
        try {
            while (queue.isEmpty()) {
                // 等待队列不空
                notEmpty.await();
            }
            // 省略出队操作...
            //出队后，通知可入队
            result = queue.get(0);
            notFull.signal();
        } finally {
            lock.unlock();
        }
        return result;
    }
}
