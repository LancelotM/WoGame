package com.unicom.game.center.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.AccountBusiness;
import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.db.domain.ChannelInfoDomain;

/**
 * @author Alex Yin
 * 
 * @Date Jun 11, 2014
 */

@Controller
public class HomeController
{
    @Autowired
    public AccountBusiness accountService;
    
    @Autowired
    public ChannelInfoBusiness channelInfoService;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		return "index";
	}
    
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(String username, String password,HttpServletRequest req){
    	ModelAndView model = new ModelAndView();
    	int flag = accountService.login(username, password);
    	if(flag == 0){
    		String url = req.getContextPath();
    		List<ChannelInfoDomain> channelInfos =  channelInfoService.fetchActiveChannelInfos();
    		model.addObject("channelInfos", channelInfos);
    		model.addObject("url", url);
    		model.setViewName("createManager");
    	}else if(flag == 1){
    		model.addObject("loginInfo", "用户不存在！");
    	}else if(flag == 2){
    		model.addObject("loginInfo", "密码错误！");
    	}
    	return model;
    }	
}
