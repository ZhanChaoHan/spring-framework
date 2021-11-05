package com.jachs.spring_jdbc.dbcp.jdbc.config;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * 
 * @author zhanchaohan
 *
 */
public class JdbcConfigCustomSeparator {
	@Test
	public void test1() throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("org/springframework/jdbc/config/jdbc-config-custom-separator.xml");
		
		DataSource dataSource=ctx.getBean("dataSource",DataSource.class);
		
//		JdbcTemplate template = new JdbcTemplate(dataSource);
		ResultSet rr=dataSource.getConnection().createStatement().executeQuery("select * from T_TEST");
		
		while(rr.next()) {
			System.out.println(rr.getString("NAME"));
		}
	}
}
