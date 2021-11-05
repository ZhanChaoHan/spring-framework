package com.jachs.spring_jdbc.dbcp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.jachs.spring_jdbc.entity.Computer;


/***
 * 
 * @author zhanchaohan
 *
 */
public class DbcpTest {
	
	@Test
	public void test1() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(new ClassPathResource("datasource/dbcp.xml"));
		
		JdbcTemplate jdbcTemplate=beanFactory.getBean("jdbcTemplate",JdbcTemplate.class);
		
		jdbcTemplate.query("select * from computer", new RowMapper<Computer>() {
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println(rs.getString(1));
				return null;
			}
			
		});
		
	}
}
