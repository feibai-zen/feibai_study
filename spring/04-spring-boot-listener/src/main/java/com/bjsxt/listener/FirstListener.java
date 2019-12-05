package com.bjsxt.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**
 * springBoot整合Listener
 *
 *<listener>
 *	<listener-class>com.bjsxt.listener.FirstListener</listener-class>
 *</listener>
 */
@WebListener
public class FirstListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener...init......");

	}

}
