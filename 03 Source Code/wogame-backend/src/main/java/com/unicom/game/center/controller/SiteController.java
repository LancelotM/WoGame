package com.unicom.game.center.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.db.domain.ChannelInfoDomain;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@Controller
public class SiteController {
	@Autowired
	private ChannelInfoBusiness channelService;
	
	@RequestMapping(value = "/createdSite", method = {RequestMethod.POST})
	public @ResponseBody List<ChannelInfoDomain> fetchActiveSites(HttpServletRequest request){
		List<ChannelInfoDomain> channelInfos = null;
		
		return channelInfos;
	}

}
