package com.jachs.spring_core.annotation;


import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * @author zhanchaohan
 */
public class ComponentScan {

    @Test
    public void test1(){
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("component-scan.xml");

        ctx.getBeanFactory().getBeanNamesIterator().forEachRemaining(a->{
            System.out.println(a);
        });
    }
}
