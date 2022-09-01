package com.jachs.spring_core.task.job2;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/***
 * 
 * @author zhanchaohan
 *
 */
@Component
public class Job2 {
	@Scheduled(cron = "0/10 * * * * ?")
	public void run(){
        System.out.println("Ann job2....."+new Date());
    }
}
