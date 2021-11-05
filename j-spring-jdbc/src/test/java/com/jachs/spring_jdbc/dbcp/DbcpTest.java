package com.jachs.spring_jdbc.dbcp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
		ApplicationContext ctx = new ClassPathXmlApplicationContext("datasource/dbcp.xml");

		JdbcTemplate jdbcTemplate=ctx.getBean("jdbcTemplate",JdbcTemplate.class);
		
		List<Computer> cpList=jdbcTemplate.query("select * from computer", new RowMapper<Computer>() {
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Computer cp=new Computer();
				cp.setMadeTime(rs.getTime("madeTime"));
				cp.setComputerId(rs.getString("computerId"));
				cp.setComputerName(rs.getString("computerName"));
				cp.setComputerPrice(rs.getLong("computerPrice"));
				return cp;
			}
		});
		for (Computer cp:cpList) {
			System.out.println(cp.getComputerName());
		}
	}
}
