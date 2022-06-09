package com.jachs.spring_rmi_client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/***
 * 
 * @author zhanchaohan
 *
 */
public class ClientTest {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-rmi-client.xml");
		
		HelloRMIService hms=context.getBean("myClient",HelloRMIService.class);
		
		System.out.println(hms.getAdd(5, 5));
	}
}