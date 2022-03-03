package com.jachs.spring_scheduling.concurrent;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/***
 * @author zhanchaohan
 */
public class Pool_01Test {

    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/scheduling/concurrent/pool_01.xml");

        ThreadPoolTaskExecutor pool= ctx.getBean("poolTaskExecutor",ThreadPoolTaskExecutor.class);

        pool.execute(()->{
            System.out.println("滴滴");
        });
        pool.execute(()->{
            System.out.println("哒哒");
        });
    }
}
