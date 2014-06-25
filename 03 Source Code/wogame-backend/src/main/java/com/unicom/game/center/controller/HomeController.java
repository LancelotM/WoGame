package com.unicom.game.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private AccountBusiness accountService;
    
	@Autowired
	private ChannelInfoBusiness channelService;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		return "index";
	}
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(
    		@RequestParam(value = "username", required = true) String username,
    		@RequestParam(value = "password", required = true) String password){
    	ModelMap model = new ModelMap();

        int flag = accountService.login(username, password);
    	if(flag == 0){
    		List<ChannelInfoDomain> channelInfos = channelService.fetchActiveChannelInfos();
			model.put("channelInfos", channelInfos);
			return new ModelAndView("/site", model);	    		
    	}else if(flag == 1){
			model.put("loginInfo", "用户不存在！");
			model.put("username", username);
			model.put("password", password);
			return new ModelAndView("index", model);    		
    	}else if(flag == 2){
			model.put("loginInfo", "密码错误！");
			model.put("username", username);
			model.put("password", password);
			return new ModelAndView("index", model);      		
    	}else{
			model.put("loginInfo", "登录失败，请重新登录！");
			model.put("username", username);
			model.put("password", password);
			return new ModelAndView("index", model);    		
    	}
    	
    }	
}
