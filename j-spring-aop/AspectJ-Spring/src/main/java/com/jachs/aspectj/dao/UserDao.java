package com.jachs.aspectj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jachs.aspectj.entity.T_User;

/**
 * @author zhanchaohan
 * 
 */
@Service
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    public void save ( T_User user ) {
        String sql = "insert into t_user(uId,uName,uPwd) values(?,?,?)";
        jdbcTemplate.update(sql, user.getUId (),user.getUName (),user.getUPwd ());
        jdbcTemplate.execute ( sql );
    }

}
