package com.jachs.spring_transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

/***
 * 
 * @author zhanchaohan
 *
 */
public class TestDB {
	
	@Test
	public void test1() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("db/spring-druid.xml");
		
		DruidDataSource dds=ctx.getBean("dSource",DruidDataSource.class);
		
		DruidPooledConnection dConn=dds.getConnection();
		
		Connection conn=dConn.getConnection();
		
		Statement statement=conn.createStatement();
		
		ResultSet rs=statement.executeQuery("select * from computer");
		
		while(rs.next()) {
			System.out.println(rs.getString("computerName"));
		}
	}
}
