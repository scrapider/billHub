package com.qzy.boot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginHandleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser!=null){
            return true;
        }
        request.setAttribute("msg", "没有权限，请先登录");
        request.getRequestDispatcher("/index.html").forward(request, response);
        return false;
    }
}

