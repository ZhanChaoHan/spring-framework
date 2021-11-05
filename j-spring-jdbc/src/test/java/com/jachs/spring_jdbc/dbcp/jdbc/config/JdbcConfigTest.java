package com.jachs.spring_jdbc.dbcp.jdbc.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * 
 * @author zhanchaohan
 *
 */
public class JdbcConfigTest {

	@Test
	public void test1() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("org/springframework/jdbc/config/jdbc-config.xml");
		
		DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
		DataSource h2DataSource=ctx.getBean("h2DataSource",DataSource.class);
		//DataSource derbyDataSource=ctx.getBean("derbyDataSource",DataSource.class);
		
		queryTable(dataSource);
		queryTable(h2DataSource);
		//queryTable(derbyDataSource);
	}
	private void queryTable(DataSource ds) throws SQLException {
		Connection conn=ds.getConnection();
		
		ResultSet rr=conn.createStatement().executeQuery("select * from T_TEST");
		
		while(rr.next()) {
			System.out.println(rr.getString("Name"));
		}
		rr.close();
		conn.close();
	}
}
