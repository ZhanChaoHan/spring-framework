package com.jachs.spring_scheduling.scheduling.quartz;

import org.junit.Test;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/***
 * 
 * @author zhanchaohan
 *
 */
public class DatabasePersistenceTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/scheduling/quartz/databasePersistence.xml");

	}

}
