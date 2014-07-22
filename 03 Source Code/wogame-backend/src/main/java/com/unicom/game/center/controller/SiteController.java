package com.unicom.game.center.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unicom.game.center.model.ChannelModel;
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
import com.unicom.game.center.business.SyncChannelClient;
import com.unicom.game.center.db.domain.ChannelInfoDomain;
import com.unicom.game.center.model.ChannelInfo;

/**
 * @author Alex Yin
 *
 * @Date 2014-6-24
 */
@Controller
public class SiteController {

    @Autowired
    private ChannelInfoBusiness channelService;
    
    @Autowired
    private SyncChannelClient syncChannelClient;

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

    @RequestMapping(value = "/startSite", method = {RequestMethod.POST})
    public ModelAndView startChannel(@RequestParam(value = "channelName", required = true) String channelName,
                                     @RequestParam(value = "channelCode", required = true) String channelCode,
                                     @RequestParam(value = "cpid", required = true) String cpid){
        ModelMap modelMap = new ModelMap();
        ChannelInfoDomain channelInfo = null;
       if(channelName != null && channelName.length()> 0 &&channelCode != null && channelCode.length()>0 && cpid != null && cpid .length()>0){
           channelInfo = channelService.startChannel(channelCode,channelName,cpid);

           if(channelInfo != null){
               String wapURL = wapLink + channelInfo.getWapToken();
               String logURL = logLink + channelInfo.getLogToken();
               channelInfo.setWapToken(wapURL);
               channelInfo.setLogToken(logURL);
               modelMap.put("channelInfoDomain", channelInfo);
               syncChannelClient.syncChannel(0, channelInfo.getChannelId(), channelCode, channelName);
           }
        }
        List<ChannelInfo> channelInfos = channelService.fetchActiveChannelInfos();
        if(null != channelInfos){
            modelMap.put("channelInfos", channelInfos);
        }
        return new ModelAndView("/site", modelMap);
    }


    @RequestMapping(value = "/getChannel", method = {RequestMethod.GET})
    public @ResponseBody ChannelInfo getActiveInfo(@RequestParam(value = "channelId", required = true) int channelId,HttpServletResponse response){
        return channelService.fetchChannelInfoById(channelId);
    }

    @RequestMapping(value = "/getChaByName", method = {RequestMethod.POST})
    public @ResponseBody ChannelInfo getChaByName(@RequestParam(value = "channelName", required = true) String channelName,HttpServletResponse response){
        ChannelInfo channelInfo = channelService.fetchChannelInfoByName(channelName);
        if(channelInfo != null){
            String wapURL = wapLink + channelInfo.getWapToken();
            String logURL = logLink + channelInfo.getLogToken();
            channelInfo.setWapToken(wapURL);
            channelInfo.setLogToken(logURL);
        }
        return channelInfo;
    }

    @RequestMapping(value = "/getChannelDetail", method = {RequestMethod.GET})
    public ModelAndView getChanneldetail(@RequestParam(value = "channelId", required = true) int channelId){
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("log");
        return modelView;
    }

    @RequestMapping(value = "/updateSit", method = {RequestMethod.POST})
    public ModelAndView updateSite(@RequestParam(value = "channelId", required = true) int channelId,
                                   @RequestParam(value = "channelCode", required = true) String channelCode,
                                   @RequestParam(value = "cpid", required = true) String cpId){
        ModelMap modelMap = new ModelMap();
        boolean flag = channelService.updateChannel(channelId,channelCode,cpId);
        if(flag){
        	syncChannelClient.syncChannel(1, channelId, channelCode, null);
        }
        modelMap.put("updateFlag",flag);
        List<ChannelInfo> channelInfos = channelService.fetchActiveChannelInfos();
        if(null != channelInfos){
            modelMap.put("channelInfos", channelInfos);
        }
        return new ModelAndView("/site",modelMap);
    }

    @RequestMapping(value="/getChannels",method = {RequestMethod.GET})
    public @ResponseBody List<ChannelModel> getChannel(){
        return channelService.getChannelMap();
    }


}
