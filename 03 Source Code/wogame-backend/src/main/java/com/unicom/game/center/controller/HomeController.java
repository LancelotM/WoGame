package com.unicom.game.center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.AccountBusiness;

/**
 * @author Alex Yin
 * 
 * @Date Jun 11, 2014
 */

@Controller
public class HomeController
{
    @Autowired
    private AccountBusiness account;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		return "index";
	}
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(
    		@RequestParam(value = "username", required = true) String username,
    		@RequestParam(value = "password", required = true) String password){
    	ModelAndView model = new ModelAndView();

        int flag = account.login(username, password);
    	if(flag == 0){
    		model.setViewName("site");
    	}else if(flag == 1){
    		model.addObject("loginInfo", "用户不存在！");
    	}else if(flag == 2){
    		model.addObject("loginInfo", "密码错误！");
    	}
    	return model;
    }	
}
