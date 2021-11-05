package com.jachs.spring_core.ioc;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * @author zhanchaohan
 *
 */
public class IocTest {

	@Test
	public void test1() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("Ioc.xml"));
		
		Computer cp=(Computer) beanFactory.getBean("myPc");
		
		cp.getMouse().click();
		cp.getMouse().showInfo();
	}
}
