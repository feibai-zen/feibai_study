package com.my.study.demos.multithread.foundation.schedule;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(HelloJob.class);

	public HelloJob() {
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Hello World! - " + new Date());
	}

}
