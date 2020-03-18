package com.feibai.designpatterns.study.observer.observer3;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 对于报社的抽象，实现了被观察者接口，每隔2s发送一次报纸
 */
public class NewsProvider implements MyObserverable {
    private static final long DELAY = 2 * 1000;
    private List<MyObserver> mObservers; //我们用一个List来维护所有的观察者对象

    public NewsProvider() {
        mObservers = new ArrayList<>();
        generateNews();
    }

    /**
     * 模拟产生新闻，每个2s发送一次
     */
    private void generateNews() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int titleCount = 1;
            int contentCount = 1;

            @Override
            public void run() {
                send(new NewsModel("title:" + titleCount++, "content:" + contentCount++));
            }
        }, DELAY, 1000);
    }

    @Override
    public void register(MyObserver myObserver) {
        if (myObserver == null)
            return;
        synchronized (this) {
            if (!mObservers.contains(myObserver))
                mObservers.add(myObserver);
        }
    }

    @Override
    public synchronized void remove(MyObserver myObserver) {
        mObservers.remove(myObserver);
    }

    @Override
    public void send(NewsModel model) {
        for (MyObserver observer : mObservers) {
            observer.receive(model);
        }
    }
}
