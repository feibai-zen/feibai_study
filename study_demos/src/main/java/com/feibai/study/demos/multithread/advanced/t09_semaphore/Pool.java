package com.feibai.study.demos.multithread.advanced.t09_semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {
    //管理计数信号量
    private final Semaphore available;
    //管理对象列表
    private final List<T> items;
    //管理对象列表上相应下标的对象的签出情况
    private final boolean[] checkedFlag;
    private final int size;

    public Pool(Class<T> classObject, int size) throws InterruptedException {
        this.size = size;
        checkedFlag = new boolean[size];
        available = new Semaphore(size);
        items = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            try {
                items.add(classObject.newInstance());
            } catch (Exception e) {
                throw new InterruptedException(e.getMessage());
            }
        }
    }

    //签出对象池的信号量
    public T checkOut() throws InterruptedException {
        T checked = null;
        try {
            available.acquire();
            checked = getItem();
            return checked;
        } finally {
            checkIn(checked);
            available.release();
        }
    }

    //签入对象池的信号量
    public void checkIn(T x) throws InterruptedException {
        try {
            available.acquire();
            releaseItem(x);
        } finally {
            available.release();
        }
    }

    private T getItem() {
        for (int i = 0; i < size; i++) {
            if (!checkedFlag[i]) {
                checkedFlag[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private void releaseItem(T x) {
        int index = items.indexOf(x);
        if (index == -1)
            return;
        if (checkedFlag[index]) {
            checkedFlag[index] = false;
        }
    }

}