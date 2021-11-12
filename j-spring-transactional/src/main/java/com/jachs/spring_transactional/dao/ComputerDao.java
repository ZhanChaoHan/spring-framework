package com.jachs.spring_transactional.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.jachs.spring_transactional.entity.Computer;

/***
 * 
 * @author zhanchaohan
 *
 */
public class ComputerDao {
	private JdbcTemplate temp;
	
	
	public int save(Computer computer) {
		return temp.update("insert into computer(computerId,computerName,computerPrice,madeTime) values(?,?,?,?)",
				computer.getComputerId(),computer.getComputerName(),
				computer.getComputerPrice(),computer.getMadeTime());
	}

	public JdbcTemplate getTemp() {
		return temp;
	}
	public void setTemp(JdbcTemplate temp) {
		this.temp = temp;
	}
}
