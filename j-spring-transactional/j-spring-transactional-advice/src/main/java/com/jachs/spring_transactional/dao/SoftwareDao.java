package com.jachs.spring_transactional.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.jachs.spring_transactional.entity.Computer;
import com.jachs.spring_transactional.entity.Software;

/***
 * @author zhanchaohan
 */
public class SoftwareDao {
    private JdbcTemplate temp;

    public JdbcTemplate getTemp() {
        return temp;
    }
    public void setTemp(JdbcTemplate temp) {
        this.temp = temp;
    }


	public void saveCpAndSw(Computer cp, Software sw) {
    	int cId=temp.update("insert into computer(computerId,computerName,computerPrice,madeTime) values(?,?,?,?)",
    			cp.getComputerId(),cp.getComputerName(),
    			cp.getComputerPrice(),cp.getMadeTime());

    	temp.update("insert into software(softWareId,cId,softWareName) values(?,?,?)",
    			sw.getSoftWareId(),sw.getCId(),sw.getSoftWareName());
	}

}
