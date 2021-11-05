package com.jachs.j_spring_aop.my;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jachs.j_spring_aop.controller.PersonController;
import com.jachs.j_spring_aop.entity.Person;

/***
 * 
 * @author zhanchaohan
 *
 */
public class PersonTest {
	@Test
	public void test() {
		Person ps=new Person("jack",12,"accvv45@mj.com");
		System.out.println("pName:\t"+ps.getpName()+",pAge:\t"+ps.getpAge()+",pEmail:\t"+ps.geteMail());
		ApplicationContext ctx = new ClassPathXmlApplicationContext("my/person/Person.xml","my/person/PersonAdvice.xml");
		
		PersonController pc=ctx.getBean("pController",PersonController.class);
		pc.save(ps);
	}
}
