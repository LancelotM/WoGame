package com.unicom.game.center.web;

import com.unicom.game.center.service.StatisticsLogger;
import com.unicom.game.center.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by jession on 14-7-2.
 */
@Controller
@RequestMapping(value = "/logUsage")
public class UsageLoggerController {

    @Autowired
    private StatisticsLogger statisticsLogger;

    private Logger logger = LoggerFactory.getLogger(UsageLoggerController.class);

    @RequestMapping
    @ResponseBody
    public String logUsage(@RequestParam("data") String data, HttpSession session) {
        String channel = (String) session.getAttribute(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID);
        Map<String, Map<String, String>> loggerData = null;
        try {
            loggerData = JsonMapper.nonDefaultMapper().fromJson(URLDecoder.decode(data, "UTF-8"), Map.class);
        } catch (UnsupportedEncodingException e) {
            logger.error("Statistics Logger Parse Parameter Error.", e);
            return "{\"result\":\"error\"}";
        }

        loggerData.get(loggerData.keySet().iterator().next())
                .put(Constants.LOGGER_CONTENT_NAME_CHANNEL_ID, channel);

        statisticsLogger.log(loggerData);

        return "{\"result\":\"success\"}";
    }
}
