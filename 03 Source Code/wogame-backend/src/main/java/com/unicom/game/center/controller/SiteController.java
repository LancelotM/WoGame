package com.unicom.game.center.controller;

import com.unicom.game.center.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.db.domain.ChannelInfoDomain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@Controller
public class SiteController {
	@Autowired
	private ChannelInfoBusiness channelService;

    @RequestMapping(value = "/site", method = {RequestMethod.GET})
    public String site(HttpServletRequest request, HttpSession session) {
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            return "site";
        }else{
            return "index";
        }
    }
	
	@RequestMapping(value = "/startSite", method = {RequestMethod.POST})
    public ModelAndView startChannel(@RequestParam(value = "channelId", required = true) int channelId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("site");
        ChannelInfoDomain channelInfoDomain = channelService.startChannel(channelId);
        List<ChannelInfoDomain> channelInfos = channelService.fetchActiveChannelInfos();
        if(channelInfoDomain != null){
            modelAndView.addObject(channelInfoDomain);
        }

        if(null != channelInfos){
            modelAndView.addObject(channelInfos);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/getActiveInfo", method = {RequestMethod.GET})
    public @ResponseBody String getActiveInfo(@RequestParam(value = "channelId", required = true) int channelId,HttpServletResponse response){
        ChannelInfoDomain channelInfo = channelService.fetchChannelInfo(channelId);
        if(null != channelInfo && Constant.ACTIVE_STATUS_ID == channelInfo.getStatus().getStatusId()){
            return String.valueOf(true);
        }
        return String.valueOf(false);
    }

    @RequestMapping(value = "/getChannelDetail", method = {RequestMethod.GET})
    public ModelAndView getChanneldetail(@RequestParam(value = "channelId", required = true) int channelId){
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("log");
        return modelView;
    }

}
