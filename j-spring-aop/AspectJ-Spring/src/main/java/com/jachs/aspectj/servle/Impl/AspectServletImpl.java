package com.jachs.aspectj.servle.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jachs.aspectj.dao.UserDao;
import com.jachs.aspectj.servle.AspectServlet;

/**
 * @author zhanchaohan
 * 
 */
@Service
public class AspectServletImpl implements AspectServlet {
    @Autowired
    private UserDao userDao;
    
    
}
