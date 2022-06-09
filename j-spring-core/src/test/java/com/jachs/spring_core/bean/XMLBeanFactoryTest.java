package com.jachs.spring_core.bean;

import com.jachs.spring_core.bean.lookup.GetBeanTest;
import com.jachs.spring_core.bean.replacer.TestChangMethod;
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

		MockConnection mc=beanFactory.getBean("mockConn",MockConnection.class);

		System.out.println(mc.getPeople().getName());
	}
	//获取器注入是一种特殊的方法注入把方法声明为返回类型的bean,但实际返回的bean是配置文件的，实现可插拔功能
	@Test
	public void testLookup(){
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("XMLBeanFactory.xml"));

		GetBeanTest gtest=beanFactory.getBean("getBentTest", GetBeanTest.class);
		gtest.showMe();
	}
	//获取器注入是一种特殊的方法注入把方法声明为返回类型的bean,但实际返回的bean是配置文件的，实现可插拔功能
	@Test
	public void testReplacer(){
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("XMLBeanFactory.xml"));

		TestChangMethod gtest=beanFactory.getBean("testChangMethod", TestChangMethod.class);
		gtest.changMe();
	}

}
