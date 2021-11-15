package com.jachs.spring_transactional.db;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jachs.spring_transactional.dao.ComputerDao;
import com.jachs.spring_transactional.entity.Computer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/***
 * 手动开启事务
 * @author zhanchaohan
 *
 */
public class ComputerDaoTest {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("db/spring-druid.xml");
	
	@Test
	public void test1() {
		ComputerDao cDao=ctx.getBean("cDao",ComputerDao.class);
		
		Computer computer=new Computer();
		
		computer.setComputerId("ISD");
		computer.setComputerName("喝大力出奇迹");
		computer.setComputerPrice(15428L);
		computer.setMadeTime(new Date());
		cDao.save(computer);
	}
	@Test
	public void test2(){
		ComputerDao cDao=ctx.getBean("cDao",ComputerDao.class);

		RandomStringUtils rsu=new RandomStringUtils();
		DataSourceTransactionManager dstm=ctx.getBean("txManager",DataSourceTransactionManager.class);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。

		TransactionStatus status = dstm.getTransaction(def);
		int max=10;//10条一次提交
		try {
			//逻辑代码，可以写上你的逻辑处理代码
			for (int kk=0;kk<=max;kk++){
				Computer computer=new Computer();

				computer.setComputerId(rsu.randomAlphanumeric(255));
				computer.setComputerName(rsu.random(255,"可以一半吃一半放生的鱼,煮豆燃豆萁,一大堆文字"));
				computer.setComputerPrice(15428L);
				computer.setMadeTime(new Date());
				cDao.save(computer);

				if((kk+1)==max){
					throw new Exception("手动抛异常测试回滚");
				}
				if(kk==max){
					dstm.commit(status);
				}

			}
		} catch (Exception e) {
			dstm.rollback(status);
			e.printStackTrace();
		}
	}
}
