package com.jachs.spring_rmi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * 
 * @author zhanchaohan
 *
 */
public class ServerTest {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("spring-rmi-service.xml");
	}
}