package com.my.study.workspace.utils;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		// 应用启动之后要执行的操作
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
