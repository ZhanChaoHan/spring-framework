package com.jachs.j_spring_aop.my;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jachs.j_spring_aop.controller.UserController;

/***
 * 
 * @author zhanchaohan
 *
 */
public class UserTest {
	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("my/user/User.xml","my/user/UserAdvice.xml");
	
		UserController uc=ctx.getBean("uController",UserController.class);
		uc.save();
		uc.query();
	}
}
