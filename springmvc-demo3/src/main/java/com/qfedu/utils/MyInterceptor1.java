package com.qfedu.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class MyInterceptor1 implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("........预处理");
        Enumeration<String> keys = request.getParameterNames();
        while(keys.hasMoreElements()){
            String key=keys.nextElement();
            if("bookId".equals(key)){
                return true;
            }
        }
        response.setStatus(400);
        return  false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("tips","通过拦截器处理后的数据");
        System.out.println("......后处理");

    }
}
