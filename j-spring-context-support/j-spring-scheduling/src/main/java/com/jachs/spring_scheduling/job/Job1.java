package com.jachs.spring_scheduling.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Job1 {
	public void test1(){
		System.out.println("Job1 Test1......");
	}
}
