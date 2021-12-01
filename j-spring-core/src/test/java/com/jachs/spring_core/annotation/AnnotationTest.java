package com.jachs.spring_core.annotation;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zhanchaohan
 *
 */
public class AnnotationTest {
	
	@Test
	public void test1() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("Annotation.xml");
		Zoo zoo = ctx.getBean("zoo", Zoo.class);

		zoo.printTiger();
		zoo.printMonkey();

		ctx.registerShutdownHook();//关闭钩子触发destory
	}
}
