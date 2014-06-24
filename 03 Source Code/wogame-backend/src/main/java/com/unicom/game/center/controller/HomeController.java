package com.unicom.game.center.controller;

import com.unicom.game.center.db.dao.AccountDao;
import com.unicom.game.center.db.domain.AccountDomain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import com.unicom.game.center.business.*;

/**
 * @author Alex Yin
 * 
 * @Date Jun 11, 2014
 */

@Controller
public class HomeController
{
    @Resource
    public AccountBusiness accountService;
    
    @Resource
    public ChannelInfoBusiness channelInfoService;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		return "index";
	}
    
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(String username, String password,HttpServletRequest req){
    	ModelAndView mad = new ModelAndView();
    	int flag = accountService.login(username, password);
    	if(flag == 0){
    		String url = req.getContextPath();
    		List<ChannelInfoDomain> channelInfos =  channelInfoService.fetchActiveChannelInfos();
    		mad.addObject("channelInfos", channelInfos);
    		mad.addObject("url", url);
    		mad.setViewName("createManager");
    	}else if(flag == 1){
    		mad.addObject("loginInfo", "用户不存在！");
    	}else if(flag == 2){
    		mad.addObject("loginInfo", "密码错误！");
    	}
    	return mad;
    }	
}
