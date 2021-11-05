package com.jachs.spring_core.bean;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/***
 * 
 * @author zhanchaohan
 *
 */
public class XMLBeanFactoryTest {

	/***
	 * 三种注入方式
	 */
	@Test
	public void test1() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("XMLBeanFactory.xml"));

		People people = (People) beanFactory.getBean("jack");

		System.out.println(people.getName());
		System.out.println(people.getAge());
		System.out.println(people.getPhoneNum());

		System.out.println("-----------------------------------");
		Person person = (Person) beanFactory.getBean("jason");

		System.out.println(person.getName());
		System.out.println(person.getAge());
		System.out.println(person.getPhoneNum());

		System.out.println("-----------------------------------");

		People personCk = (People) beanFactory.getBean("jessica");

		System.out.println(personCk.getName());
		System.out.println(personCk.getAge());
		System.out.println(personCk.getPhoneNum());
	}

	@Test
	public void testConnection() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("XMLBeanFactory.xml"));
	}
}
