package com.jachs.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.aspectj.dao.UserDao;
import com.jachs.aspectj.entity.T_User;

/**
 * @author zhanchaohan
 * 
 */
@Aspect
@SpringBootTest
public class AspectJTest {
    @Autowired
    private UserDao userDao;
    
    @Test
    public void startAt() {
        T_User user=new T_User("1K");
        user.setUName ( "jack" );
        user.setUPwd ( "pOne" );
        userDao.save ( user );
    }
}
