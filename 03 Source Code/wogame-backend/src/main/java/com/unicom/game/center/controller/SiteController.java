package com.unicom.game.center.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.game.center.business.ChannelInfoBusiness;
import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.model.ChannelInfo;
import com.unicom.game.center.utils.Constant;

/**
 * @author Alex Yin
 * 
 * @Date 2014-6-24
 */
@Controller
public class SiteController {
	@Autowired
	private ChannelInfoBusiness channelService;
	
	@Value("#{properties['wogame.wap.link']}")
	private String wapLink;
	
	@Value("#{properties['wogame.log.link']}")
	private String logLink;		

    @RequestMapping(value = "/site", method = {RequestMethod.GET})
    public ModelAndView site(HttpServletRequest request, HttpSession session) {
    	ModelMap model = new ModelMap();
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            List<ChannelInfo> channelInfos = channelService.fetchActiveChannelInfos();
            if(null != channelInfos){
                model.put("channelInfos", channelInfos);
            }
            
            return new ModelAndView("/site", model);
        }else{
            return new ModelAndView("/index", model);
        }
    }
	
//	@RequestMapping(value = "/startSite", method = {RequestMethod.POST})
//    public ModelAndView startChannel(@RequestParam(value = "channelId", required = true) int channelId){
//		ModelMap model = new ModelMap();
//        ChannelInfoDomain channelInfoDomain = channelService.startChannel(channelId);        
//        if(channelInfoDomain != null){
//        	String wapURL = wapLink + channelInfoDomain.getWapToken();
//        	String logURL = logLink + channelInfoDomain.getLogToken();
//        	channelInfoDomain.setWapToken(wapURL);
//        	channelInfoDomain.setLogToken(logURL);
//        	model.put("channelInfoDomain", channelInfoDomain);
//        }
//
//        List<ChannelInfo> channelInfos = channelService.fetchActiveChannelInfos();
//        if(null != channelInfos){
//            model.put("channelInfos", channelInfos);
//        }
//        
//        return new ModelAndView("/site", model);
//    }
//
//    @RequestMapping(value = "/getActiveInfo", method = {RequestMethod.GET})
//    public @ResponseBody String getActiveInfo(@RequestParam(value = "channelId", required = true) int channelId,HttpServletResponse response){
//        ChannelInfoDomain channelInfo = channelService.fetchChannelInfo(channelId);
//        if(null != channelInfo && Constant.ACTIVE_STATUS_ID == channelInfo.getStatus().getStatusId()){
//            return String.valueOf(true);
//        }
//        return String.valueOf(false);
//    }

    @RequestMapping(value = "/getChannelDetail", method = {RequestMethod.GET})
    public ModelAndView getChanneldetail(@RequestParam(value = "channelId", required = true) int channelId){
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("log");
        return modelView;
    }

}
