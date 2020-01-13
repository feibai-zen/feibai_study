package com.feibai.study.demos.good_practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public enum ThreadPool {

	INSTANCE;

	private ExecutorService pool = Executors.newCachedThreadPool();
	private ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);

	public ScheduledFuture<?> schedule(Runnable action, long initialDelay, long delay, TimeUnit unit) {
		return scheduledPool.scheduleWithFixedDelay(action, initialDelay, delay, unit);
	}

	public Future<?> submit(Runnable action) {
		return pool.submit(action);
	}

	public <T> Future<T> submit(Callable<T> action) {
		return pool.submit(action);
	}

	public List<Future<Object>> invokeAll(List<Runnable> rs) {
		List<Callable<Object>> clist = new ArrayList<>();
		for (Runnable runnable : rs) {
			clist.add(Executors.callable(runnable));
		}
		return invokeAll(clist);
	}

	public <T> List<Future<T>> invokeAll(Collection<Callable<T>> clist) {
		try {
			return pool.invokeAll(clist);

		} catch (Exception e) {
			return null;
		}
	}

	public <T> void waitAllTask(List<Future<T>> futureList) throws Exception {
		for (Future<T> future : futureList) {
			try {
				future.get();

			} catch (Exception e) {

			}
		}
	}

	public boolean stopScheduledTask(ScheduledFuture<?> future) {
		if (future == null) {
			return true;
		}
		if (future.isDone()) {
			return true;
		}
		return future.cancel(false);

	}

	public <T> T waitTask(Future<T> future) {
		try {
			return future.get();
		} catch (Exception e) {
			return null;
		}
	}

}
