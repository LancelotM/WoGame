package com.unicom.game.center.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.unicom.game.center.db.domain.AccountDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.AccountBusiness;
import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.model.ChannelInfo;

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

    @RequestMapping(value = "/checkNamePwd", method = {RequestMethod.POST})
    public @ResponseBody Integer checkNamePwd(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request, HttpSession session){
        return accountService.login(username, password);
    }
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(
    		@RequestParam(value = "username", required = true) String username,
    		@RequestParam(value = "password", required = true) String password,
    		@RequestParam(value = "remember", required = false) boolean remember,
    		HttpServletRequest request, HttpSession session){
    	ModelMap model = new ModelMap();
    	
		if(null == session){
			session = request.getSession(true);
		}

        int flag = accountService.login(username, password);
    	if(flag == 0){
    		List<ChannelInfo> channelInfos = channelService.fetchActiveChannelInfos();
			model.put("channelInfos", channelInfos);
			session.setAttribute("admin", true);
			return new ModelAndView("/site", model);	    		
    	}
        return new ModelAndView("index", model);

    }
    
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) 
	{
		ModelMap model = new ModelMap();

		if(null != session)
		{
			session.invalidate();
		}
		
		return new ModelAndView("/index", model);
	}    
}
