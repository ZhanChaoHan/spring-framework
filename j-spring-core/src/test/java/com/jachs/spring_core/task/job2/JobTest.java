package com.jachs.spring_core.task.job2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * 
 * @author zhanchaohan
 *
 */
public class JobTest {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/task/Job2.xml");
		
	}
}
