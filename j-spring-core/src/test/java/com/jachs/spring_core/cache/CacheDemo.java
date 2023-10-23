package com.jachs.spring_core.cache;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

/***
 * 
 * @author zhanchaohan
 *
 */
public class CacheDemo {

	@Test
	public void t1() throws Exception {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("cache/Cache.xml");

		SpringCacheService scs=ctx.getBean("springCacheService",SpringCacheService.class);

		StopWatch watch=new StopWatch();
		watch.start();
		scs.findPeopleByName("t1");

		scs.findPeopleByName("t1");



		System.out.println(watch.prettyPrint());


	}
}
