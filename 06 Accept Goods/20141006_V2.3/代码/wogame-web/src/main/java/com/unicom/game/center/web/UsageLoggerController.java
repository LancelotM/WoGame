package com.unicom.game.center.web;

import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by jession on 14-7-2.
 */
@Controller
public class UsageLoggerController {

    @Autowired
    private StatisticsLogger statisticsLogger;

    private Logger logger = LoggerFactory.getLogger(UsageLoggerController.class);

    @RequestMapping("/businessLog")
    @ResponseBody
    public String businessLog(@RequestParam("data") String data, HttpSession session) {
        String channel = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
        if(null == channel){
        	channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
        }

        String[] logData = new String[]{data, channel};

        statisticsLogger.info(StringUtils.join(logData, ""));

        return "{\"result\":\"success\"}";
    }

    @RequestMapping("/numberLog")
    @ResponseBody
    public String numberLog(@RequestParam("data") String data, HttpSession session) {
        String channel = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
        if(null == channel){
        	channel = com.unicom.game.center.utils.Constant.DEFAULT_CHANNLE_ID;
        }

        String[] logData = new String[]{data, channel};
        statisticsLogger.number(StringUtils.join(logData, ""));

        return "{\"result\":\"success\"}";
    }
}
