package com.jachs.spring_scheduling.scheduling.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * @author zhanchaohan
 */
public class SchedulerRepositoryExposure {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/scheduling/quartz/schedulerRepositoryExposure.xml");
    }
}
