package com.jachs.mvc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * @author zhanchaohan
 */
public class LogController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ModelAndView modelView = new ModelAndView();
        //类似于 request.setAttribute()
        modelView.addObject("name","张三");
        modelView.setViewName("/index");
        return modelView;
    }

}
