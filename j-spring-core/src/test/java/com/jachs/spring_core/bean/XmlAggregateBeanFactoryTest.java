package com.jachs.spring_core.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import com.jachs.spring_core.bean.list.Aggregate;

/**
 * 
 * @author zhanchaohan
 *
 */
public class XmlAggregateBeanFactoryTest {

	@Test
	public void test1() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("XmlAggregateBeanFactory.xml"));

		Aggregate aggregate = (Aggregate) beanFactory.getBean("aggregate");

		String[] sList = aggregate.getStrList();
		System.out.println("sList-----------------------------------------");
		for (String str : sList) {
			System.out.println(str);
		}
		int[] intList = aggregate.getIntList();
		System.out.println("sList-----------------------------------------");
		for (int ins : intList) {
			System.out.println(ins);
		}
		List<String>sAList=aggregate.getsList();
		System.out.println("sAList-----------------------------------------");
		for (String ins : sAList) {
			System.out.println(ins);
		}
		List<Person>pList=aggregate.getPerList();
		System.out.println("pList-----------------------------------------");
		for (Person person : pList) {
			System.out.println(person.getName()+"\t\t"+person.getAge()+"\t\t"+person.getPhoneNum());
		}
		Set<String>setSet=aggregate.getSetSet();
		System.out.println("setSet-----------------------------------------");
		for (String string : setSet) {
			System.out.println(string);
		}
		Set<Person>perSet=aggregate.getPerSet();
		System.out.println("perSet-----------------------------------------");
		for (Person person : perSet) {
			System.out.println(person.getName()+"\t\t"+person.getAge()+"\t\t"+person.getPhoneNum());
		}
		Map<String, Integer>map=aggregate.getMap();
		System.out.println("map-----------------------------------------");
		for (String key : map.keySet()) {
			System.out.println(key+"\t\t"+map.get(key));
		}
		Properties pro=aggregate.getPro();
		System.out.println("pro-----------------------------------------");
		System.out.println(pro.get("uname"));
		System.out.println(pro.get("pass"));
	}
}
