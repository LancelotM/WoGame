package com.unicom.game.center.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.unicom.game.center.model.ServerLogInfo;
import com.unicom.game.center.utils.DateUtils;
import com.unicom.game.center.utils.UnicomLogServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.util.HttpClientUtil;

import java.util.Date;

/**
 * 管理员管理用户的Controller.
 *
 * @author calvin
 */
@Controller
public class ChangWanController {
	
    @Autowired
    private StatisticsLogger statisticsLogger;

    @Autowired
    private UnicomLogServer unicomLogServer;

    @RequestMapping(value = "/changWan")
    public String changWan(HttpServletRequest request) {
        HttpSession session = HttpClientUtil.fetchSession(request);            

        String clientIP = (String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CLIENT_IP);
    	String channel = (String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
        if(null == channel){
        	channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
        } 

    	String[] logData = new String[]{"65", channel, clientIP};
    	statisticsLogger.pageview(StringUtils.join(logData, "|"));

        String date = DateUtils.formatDateToString(new Date(), "yyyy年MM月dd日 hh:mm:ss");
        ServerLogInfo serverLogInfo = new ServerLogInfo();
        serverLogInfo.setPageName("0元畅玩");
        serverLogInfo.setChannelCode((String)session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_CODE));
        serverLogInfo.setIp(clientIP);
        serverLogInfo.setDate(date);

        Gson gson = new Gson();
        unicomLogServer.pageviewLog(gson.toJson(serverLogInfo));
        return "member";
    }


}
