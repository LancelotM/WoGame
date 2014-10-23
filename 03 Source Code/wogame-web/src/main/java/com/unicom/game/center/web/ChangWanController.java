package com.unicom.game.center.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
public class ChangWanController {
	
    @Autowired
    private StatisticsLogger statisticsLogger;

    @RequestMapping(value = "/changWan")
    public String changWan(@RequestParam(value="weixin", required=false) String weixin, Model model, HttpServletRequest request, HttpSession session) {

    	String channel = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
        if(null == channel){
        	channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
        } 
        
    	String clientIP = HttpClientUtil.getClientIp(request);
    	String[] logData = new String[]{"65", channel, clientIP};
    	statisticsLogger.pageview(StringUtils.join(logData, "|"));
    	
    	if(null != weixin && "1".equals(weixin)){
    		model.addAttribute("weixin", true);
    		session.setAttribute("WEIXIN_AGENT", true);
    	}else{
    		if(null == weixin){
    			Boolean weixin_agent = (Boolean)session.getAttribute("WEIXIN_AGENT");
    			if(null != weixin_agent && weixin_agent.booleanValue()){
    	    		model.addAttribute("weixin", true);
    	    		session.setAttribute("WEIXIN_AGENT", true);    				
    			}else{
            		model.addAttribute("weixin", false);
            		session.setAttribute("WEIXIN_AGENT", false);      				
    			}
    		}else{
        		model.addAttribute("weixin", false);
        		session.setAttribute("WEIXIN_AGENT", false);    			
    		}
    	}    	
    	
        return "changWan";
    }


}
