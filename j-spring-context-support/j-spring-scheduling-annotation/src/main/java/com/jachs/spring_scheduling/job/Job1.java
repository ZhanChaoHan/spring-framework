package com.jachs.spring_scheduling.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/***
 * 
 * @author zhanchaohan
 *
 */
@Component
public class Job1 {
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void test1() {
		System.out.println("0/5 * * * * ?");
	}

	@Scheduled(fixedDelay = 1000)
	public void test2() {//固定等待时间
		System.out.println("fixedDelay = 1000");
	}

	@Scheduled(fixedRate  = 2000)
	public void test3() {//固定间隔时间
		System.out.println("fixedRate  = 2000");
	}
}
