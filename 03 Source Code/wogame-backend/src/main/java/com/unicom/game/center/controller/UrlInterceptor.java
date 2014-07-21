package com.unicom.game.center.controller;

import com.unicom.game.center.utils.Utility;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-21
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 */
public class UrlInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession sessoin = request.getSession();
//        String token = (String)sessoin.getAttribute("token");
//        if(!Utility.isEmpty(token)){
//            sessoin.setAttribute("admin",false);
//        }else{
//            sessoin.setAttribute("admin",true);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
