package com.jachs.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/***
 * @author zhanchaohan
 */
@Controller
@RequestMapping("/logs")
public class LogController  {

    @GetMapping("/log")
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ModelAndView modelView = new ModelAndView();
        modelView.addObject("name","张三");
        modelView.setViewName("/index");
        return modelView;
    }
    @RequestMapping("/log2")
    public ModelAndView cs(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ModelAndView modelView = new ModelAndView();
        modelView.addObject("name","cs");
        modelView.setViewName("/index");
        return modelView;
    }
}
