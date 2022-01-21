package com.jachs.spring_scheduling.scheduling.quartz;

import org.junit.Test;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * @author zhanchaohan
 */
public class MultipleAnonymousMethodInvokingJobDetailFBTest {

    @Test
    public void test1() throws SchedulerException {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("/scheduling/quartz/multipleAnonymousMethodInvokingJobDetailFB.xml", getClass());

        StdScheduler scheduler=context.getBean("scheduler", StdScheduler.class);

        scheduler.standby();
        scheduler.start();
    }
}
