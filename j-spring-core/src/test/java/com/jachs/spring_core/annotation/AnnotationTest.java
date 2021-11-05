package com.jachs.spring_core.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zhanchaohan
 *
 */
public class AnnotationTest {
	
	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("Annotation.xml");
		Zoo zoo = ctx.getBean("zoo", Zoo.class);

		zoo.printTiger();
		zoo.printMonkey();
	}
}
