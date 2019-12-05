package com.my.study.workspace.utils;

import java.util.concurrent.TimeUnit;

public class TimeSleepUtil {
	public static void sleepMinutes(long m) {
		try {
			TimeUnit.MINUTES.sleep(m);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void sleepSeconds(long s) {
		try {
			TimeUnit.SECONDS.sleep(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sleepMillSceond(long ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
