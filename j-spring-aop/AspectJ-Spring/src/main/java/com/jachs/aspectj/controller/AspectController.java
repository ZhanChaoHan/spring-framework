package com.jachs.aspectj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jachs.aspectj.servle.AspectServlet;

/**
 * @author zhanchaohan
 * 
 */
@Controller
public class AspectController {
    @Autowired
    private AspectServlet aspectServlet;
    
    @RequestMapping( "/asc" )
    @ResponseBody
    public String getAsc () {
        return "okzzsa";
    }
}
