package com.jachs.spring_scheduling.scheduling;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Job_01Test {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/job_1.xml");
	}

}
