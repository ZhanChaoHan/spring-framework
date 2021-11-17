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
	ApplicationContext ctx = new ClassPathXmlApplicationContext("my/user/User.xml","my/user/UserAdvice.xml");
	
	@Test
	public void test1() {
		UserController uc=ctx.getBean("uController",UserController.class);
		uc.save();
		uc.query();
	}
}
