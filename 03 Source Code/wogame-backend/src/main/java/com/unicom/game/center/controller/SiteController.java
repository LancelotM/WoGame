package com.unicom.game.center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.db.domain.ChannelInfoDomain;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@Controller
public class SiteController {
	@Autowired
	private ChannelInfoBusiness channelService;
	
	@RequestMapping(value = "/startSite", method = {RequestMethod.GET})
    public @ResponseBody ChannelInfoDomain startChannel(@RequestParam(value = "channelId", required = true) int channelId){
        ChannelInfoDomain channelInfoDomain = channelService.startChannel(channelId);
        return channelInfoDomain;
    }

    @RequestMapping(value = "/getActiveInfo", method = {RequestMethod.GET})
    public @ResponseBody String getActiveInfo(@RequestParam(value = "channelId", required = true) int channelId,HttpServletResponse response){
        boolean activeInfo = channelService.checkChannelIsActive(channelId);
        return String.valueOf(activeInfo);
    }

    @RequestMapping(value = "/exit", method = {RequestMethod.GET})
    public ModelAndView exit(){
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("index");
        return modelView;
    }

    @RequestMapping(value = "/getChannelDetail", method = {RequestMethod.GET})
    public ModelAndView getChanneldetail(@RequestParam(value = "channelId", required = true) int channelId){
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("log");
        return modelView;
    }

}
