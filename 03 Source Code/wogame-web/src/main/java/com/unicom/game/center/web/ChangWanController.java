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
    	
    	String clientIP = getClientIp(request);
    	String[] logData = new String[]{"65", "", clientIP};
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

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
            if (ip != null) {
                ip = ip.split(",")[0].trim();
            }
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
